package almeida.murilo.Enums;

public enum InclinacaoTelha {

    FIBROCIMENTO(10, 20),
    METALICA(5, 10),
    SHINGLE(15, 30),
    CERAMICA(30,35),
    CONCRETO(30,35),
    ;

    private int min;
    private int max;

    InclinacaoTelha(int min, int max){
        this.max = max;
        this.min = min;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
