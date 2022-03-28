public enum Level {
        EASY(1, "facil", 3), MEDIUM(2, "medio", 100), DIFFICULT(3, "dificl", 100);

        private final int level;
        private final String name;
        private final int movements;

        Level(int level, String name, int movements) {
            this.level = level;
            this.name = name;
            this.movements = movements;
        }

        public int getLevel() {
            return level;
        }

        public String getName() {
            return name;
        }

        public int getMovements() {
            return movements;
        }
    }


