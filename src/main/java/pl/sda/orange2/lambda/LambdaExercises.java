package pl.sda.orange2.lambda;

import java.util.ArrayList;
import java.util.List;

public class LambdaExercises {

    public static void main(String[] args) {

        BoysDontCry boy = new ChlopakiNiePlacza();
        boy.silnoreki();
        ((ChlopakiNiePlacza)boy).sing();

        ChlopakiNiePlacza boy2 = new ChlopakiNiePlacza();
        boy2.sing();
        List<ChlopakiNiePlacza> boys = new ArrayList<>();

        BoysDontCry anonymousClassReference = new BoysDontCry() {
            @Override
            public void silnoreki() {
                System.out.println("Anonimowo silnoreki");
            }
        };
        anonymousClassReference.silnoreki();

        BoysDontCry lambdaClassReference = () -> System.out.println("Lambda silnoręki");
        lambdaClassReference.silnoreki();


    }
}
