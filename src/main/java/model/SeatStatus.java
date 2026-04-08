package model;

public enum SeatStatus {

    AVAILABLE {
        @Override
        public SeatStatus reserve() {
            return RESERVED;
        }

        @Override
        public SeatStatus release() {
            throw new IllegalArgumentException("예매되지 않은 좌석은 취소할 수 없습니다.");
        }
    },

    RESERVED {
        @Override
        public SeatStatus reserve() {
            throw new IllegalArgumentException("이미 예매된 좌석입니다.");
        }

        @Override
        public SeatStatus release() {
            return AVAILABLE;
        }
    };

    public abstract SeatStatus reserve();

    public abstract SeatStatus release();

    public boolean isReserved() {
        return this == RESERVED;
    }
}
