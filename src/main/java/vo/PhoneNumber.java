package vo;

public record PhoneNumber(String value) {
    private static final String PHONE_NUMBER_PATTERN = "^\\d{3}-\\d{4}-\\d{4}$";

    public PhoneNumber {
        if (value == null || !value.matches(PHONE_NUMBER_PATTERN)) {
            throw new IllegalArgumentException("휴대폰 번호 형식이 올바르지 않습니다.");
        }
    }
}
