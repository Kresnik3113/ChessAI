import java.util.*;

public class AIAgent{
    Random rand;

    public AIAgent(){
        rand = new Random();
    }

/*
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a rondom number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/

    public  Move randomMove(Stack possibilities){

        int moveID = rand.nextInt(possibilities.size());
        System.out.println("Agent randomly selected move : "+moveID);
        for(int i=1;i < (possibilities.size()-(moveID));i++){
            possibilities.pop();
        }
        Move selectedMove = (Move)possibilities.pop();
        return selectedMove;
    }

    public Move nextBestMove(Stack possibilities){
        Square s=new Square(75,75,"BlackPawn");
        Square s1=new Square(75,75,"BlackPawn");
        Move selectedMove = new Move(s1,s,-1);
        int moveID = rand.nextInt(possibilities.size());
        Move tmp = new Move();
        int counter= possibilities.size();
        for(int i=0;i< counter;i++){
            tmp=(Move)possibilities.pop();
            for (int p=0;p<counter;p++){
                if(selectedMove.getScore()<tmp.getScore()){
                    selectedMove=tmp;
                }
            }
        }
        if(selectedMove.getScore()==0){
          for(int i=1;i < (possibilities.size()-(moveID));i++){
                possibilities.pop();
           }
            selectedMove = (Move)possibilities.pop();
       }
        System.out.println(selectedMove.getScore()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return selectedMove;
    }
//!!!!!!!!!!!!!This method is in the chess project class!!!!!!!!!!!!!!!!!!!!!
//    public Move twoLevelsDeep(Stack possibilities){
//
//        Stack temporary = (Stack)possibilities.clone();
//        Square s=new Square(75,75,"BlackPawn");
//        Square s1=new Square(75,75,"BlackPawn");
//        Move selectedMove = new Move(s1,s,-1);
//        int moveID = rand.nextInt(possibilities.size());
//        Move tmp;
//        Move tmp1,tmp2 = new Move();
//        tmp2.moveScore=-999;
//        int counter= possibilities.size();
//        for(int i=0;i< counter;i++){
//            tmp=(Move)possibilities.pop();
//            Stack black=cp.blackAttacks();
//            int counter1=black.size();
//            for(int j=0;j<counter1;j++){
//                tmp1=(Move)black.pop();
//                if(tmp1.getScore()>tmp2.getScore()){
//                    tmp2=tmp1;
//                }
//            }
//
//            for (int p=0;p<counter;p++){
//                if(selectedMove.getScore()<(tmp.getScore()- tmp2.getScore())){
//                    selectedMove=tmp;
//                }
//            }
//        }
//        if(selectedMove.getScore()==0){
//            for(int i=1;i < (temporary.size()-(moveID));i++){
//                temporary.pop();
//            }
//            selectedMove = (Move)temporary.pop();
//        }
//        System.out.println(selectedMove.getScore()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        return selectedMove;
//    }
}