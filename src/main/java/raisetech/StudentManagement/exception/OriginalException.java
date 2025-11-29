package raisetech.StudentManagement.exception;

public class OriginalException {
  public static class OriginalIdException extends Exception {

    public OriginalIdException() {
      super();
    }

    public OriginalIdException(String message) {
      super(message);
    }

    public OriginalIdException(String message, Throwable cause) {
      super(message, cause);
    }

    public OriginalIdException(Throwable cause) {
      super(cause);
    }
  }
}
