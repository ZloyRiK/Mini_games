package tic_tac_toe;

public class Counter {
    int index, depth = 5;
    private Result result; //возможно ошибка
    Counter (Result result){
        this.result = result;
    }

    public int process(String[] array){
        recursion("O", array, index, depth); // Возможно -1 вместо index и другой символ, возможно
        return index;
    }

    public int recursion(String symbol, String[] arrayOut, int point, int depth){
        String[] array = arrayOut.clone();
        if(point>=0) array[point]=symbol;
        if (result.process(array)==symbol) return 2;
        

        int score = 0, maxScore = -1, indexRec =0;
        for (int i = 0; i < array.length; i++) {
            if (array[i]==null){
                if (symbol=="X") score = recursion("O", array, i, depth-1);
                if (symbol=="O") score = recursion("X", array, i, depth -1);
                if (score>maxScore) maxScore = score; indexRec =i;
            }
        }
        this.index = indexRec;
        if(maxScore == -1) return 1;
        else return 2 - maxScore;
    }
}
