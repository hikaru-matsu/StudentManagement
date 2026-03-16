# StudentManagement (受講生管理システム)

## 概要

Spring Bootを用いた受講生管理システムです。

本プロジェクトは、アプリケーションの開発だけでなく、**「AWS環境におけるセキュアで高可用なインフラアーキテクチャの設計・構築の実践」** および **「Dockerを用いたコンテナ運用の習得」** を主目的として作成しました。

---

## 🏗 AWS インフラ構成図

<img width="1285" height="1281" alt="AWS構成図 drawio" src="https://github.com/user-attachments/assets/71f9c83d-dd8c-4f05-8a80-fc5b1523c987" />


---

## 🛠 使用技術

### インフラ・環境

| カテゴリ | 技術 |
|----------|------|
| クラウド | AWS (VPC, EC2, ALB, RDS, Internet Gateway) |
| OS | Amazon Linux 2023 |
| コンテナ | Docker / Docker Compose |

### アプリケーション

| カテゴリ | 技術 |
|----------|------|
| 言語 | Java 21 |
| フレームワーク | Spring Boot 3.5.7 |
| O/Rマッパー | MyBatis 3.0.5 |
| データベース | AWS RDS (MySQL 8.0) |
| APIドキュメント | SpringDoc OpenAPI (Swagger UI) |
| テスト | JUnit5 / Mockito / H2 (インメモリDB) |
| その他 | Lombok, Apache Commons Lang, Bean Validation |

---

## 💡 インフラ設計の意図・ポイント

AWS Well-Architected Frameworkを意識した設計を行いました。

### 1. ネットワーク層の分離とセキュリティ

VPC内にパブリックサブネットとプライベートサブネットを構築し、**Web/App層とDB層を分離**しました。  
RDSはインターネットから直接アクセスできないプライベートサブネットに配置し、セキュリティグループによって「EC2からのポート3306の通信のみ」を許可する**最小権限の原則**を適用しています。  
データベースの認証情報はソースコードにハードコーディングせず、EC2上の `.env` ファイルから環境変数として注入する設計（12 Factor App）とし、GitHubへの機密情報漏洩を防止しています。

### 2. 高可用性（HA）とスケーラビリティの確保

トラフィックの入り口として **ALB (Application Load Balancer)** を導入し、異なる2つのアベイラビリティゾーン（AZ-a, AZ-c）のパブリックサブネットを跨ぐように配置しています。  
ALBのクロスゾーン負荷分散を活用することで、将来的なEC2インスタンスのスケールアウトに即座に対応できるネットワークレイヤーを構築しました。  
データベース（RDS）については、本番運用における単一障害点（SPOF）排除のためマルチAZ配置（Primary / Standby構成）を前提としたサブネット設計を行っています。**※ただし、個人開発の運用コスト最適化のため、現在の検証環境ではあえてシングルAZで稼働させています。要件に応じて即座にマルチAZへ変更可能なアーキテクチャとしています。**

### 3. アーキテクチャ選定におけるトレードオフと検証

開発・検証の初期段階ではコスト最適化を優先し、EC2内のDockerコンテナでMySQLを稼働させる構成を採用しました。  
その後、本番運用におけるバックアップやフェイルオーバーの可用性を担保するため、**RDS接続構成へアーキテクチャを移行・検証**しました。要件とコストのバランスに応じた柔軟な構成変更を実践しています。

---

## 🚀 環境構築手順

AWSのEC2インスタンス（Amazon Linux 2023）上での起動手順です。

```bash
# リポジトリのクローン
git clone https://github.com/hikaru-matsu/StudentManagement.git
cd StudentManagement

# 環境変数の設定
vi .env
```

`.env` の内容：

```env
RDS_ENDPOINT=your-rds-endpoint.rds.amazonaws.com
RDS_USERNAME=your_rds_username
RDS_PASSWORD=your_rds_password
```

```bash
# Docker Composeによるビルドと起動
docker-compose up -d --build
```

---

## 📋 機能一覧

| 機能 | 説明 |
|------|------|
| 受講生一覧取得 | 全受講生とコース情報をまとめて取得 |
| 受講生単一検索 | IDで受講生1件とそのコース情報を取得 |
| 受講生登録 | 受講生情報とコース情報を同時に登録（トランザクション管理） |
| 受講生更新 | 受講生情報・コース情報を更新 |

---

## 🔌 APIエンドポイント

Postmanを使用してAPIの動作確認を行っています。ベースURLは以下の通りです：`http://<ALBのDNS名>`

| メソッド | パス | 説明 |
|----------|------|------|
| GET | `/studentList` | 受講生一覧取得 |
| GET | `/student/{id}` | IDで受講生を1件取得 |
| POST | `/registerStudent` | 受講生を新規登録 |
| PUT | `/updateStudent` | 受講生情報を更新 |

---

## 🧪 テスト

テストはH2インメモリDBを使用しており、MySQLなしでローカル環境でも即座に実行できます。

```bash
./gradlew test
```

| テストクラス | テスト対象 | 方式 |
|-------------|-----------|------|
| `StudentControllerTest` | Controller層 | MockMvc + Mockito |
| `StudentServiceTest` | Service層 | Mockito（単体テスト） |
| `StudentConverterTest` | Converter | 純粋な単体テスト |
| `StudentRepositoryTest` | Repository層 | @MybatisTest + H2 |

---

## 🔧 工夫した点・こだわり

**レイヤードアーキテクチャの徹底**  
Controller / Service / Repository / Converter の責務を明確に分離し、各層が独立してテストできる設計にしました。

**各層ごとのテスト**  
Controller層はMockMvcでHTTPレベルのテスト、Service層はMockitoで依存を切り離した純粋な単体テスト、Repository層はH2 + `@MybatisTest` でSQLのみを検証、ConverterはJavaクラスとして単体テストを実施しました。

**トランザクション管理**  
受講生登録・更新では `@Transactional` を使い、受講生情報とコース情報の整合性を保証しています。

**例外ハンドリング**  
`@ControllerAdvice` によるグローバル例外ハンドラを実装し、バリデーションエラーや独自例外を一元管理しています。

**セキュアな設定管理**  
機密情報は `.env` ファイルと環境変数で管理し、ソースコードへの直書きを排除しています。

---

## 🚀 今後の展望と課題

**自力でのアーキテクチャ設計力の向上**  
本プロジェクトのインフラ設計および構築においては、生成AIを技術的な壁打ち相手として活用し、AWSのベストプラクティスを学びながら実装を進めました。今後はAIの提案を取り入れるだけでなく、AWSの公式ドキュメントやネットワークの原理原則を自分自身で深く理解し、ゼロから最適なアーキテクチャ設計やトラブルシューティングが完遂できる技術力を身につけたいと考えています。
