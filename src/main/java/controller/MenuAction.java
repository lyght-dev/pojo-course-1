package controller;

public enum MenuAction {
    SHOW_SCREENINGS("1"),
    RESERVE("2"),
    SHOW_MY_TICKETS("3"),
    CANCEL_TICKET("4"),
    EXIT("5");

    private final String input;

    MenuAction(String input) {
        this.input = input;
    }

    public boolean isExit() {
        return this == EXIT;
    }

    public static MenuAction from(String input) {
        for (MenuAction action : values()) {
            if (action.input.equals(input)) {
                return action;
            }
        }
        throw new IllegalArgumentException("지원하지 않는 메뉴입니다.");
    }
}
