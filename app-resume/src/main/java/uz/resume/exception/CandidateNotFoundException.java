package uz.resume.exception;

public class CandidateNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CandidateNotFoundException() {
        super();
    }

    public CandidateNotFoundException(String customMessage) {
        super(customMessage);
    }
}
