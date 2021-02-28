package Compulsory;

import java.util.Objects;

public class Source {

        private SourceType type;
        private int capacity;

        public SourceType getType() {
                return type;
        }
        public void setType(SourceType type) {
                this.type = type;
        }
        public int getCapacity() {
                return capacity;
        }
        public void setCapacity(int capacity) {
                this.capacity = capacity;
        }

        public Source(SourceType type, int capacity) {
                this.type = type;
                this.capacity = capacity;
        }

        @Override
        public String toString() {
                return "Source{" +
                        "type=" + type +
                        ", capacity=" + capacity +
                        '}';
        }
}
