package model;

public class Tuple2 {
    private String K1;
    private String K2;

    public Tuple2(String k1, String k2) {
        K1 = k1;
        K2 = k2;
    }

    public String getK1() {
        return K1;
    }

    public void setK1(String k1) {
        K1 = k1;
    }

    public String getK2() {
        return K2;
    }

    public void setK2(String k2) {
        K2 = k2;
    }

    @Override
    public String toString() {
        return this.K2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tuple2) {
            return this.getK1().equals(((Tuple2) obj).getK1())
                    && this.getK2().equals(((Tuple2) obj).getK2());
        }
        return false;
    }
}
