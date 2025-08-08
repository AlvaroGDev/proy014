package es.cic.curso25.proy014;


public class ClaseA {

    private ClaseB claseB;


    public int metodo1(int a, int b){
        return claseB.metodo2(a, b);
    }


    public ClaseB getClaseB() {
        return claseB;
    }

    public void setClaseB(ClaseB claseB) {
        this.claseB = claseB;
    }
}
