package model;

public enum TicketStatus {

    RESERVED {
        @Override
        public TicketStatus cancel() {
            return CANCELED;
        }
    },

    CANCELED {
        @Override
        public TicketStatus cancel() {
            throw new IllegalArgumentException("이미 취소된 예매입니다.");
        }
    };

    public abstract TicketStatus cancel();

    public boolean isCanceled() {
        return this == CANCELED;
    }
}