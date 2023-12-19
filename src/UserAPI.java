public class UserAPI{
   
   public static String opponentName;
   public static int opponentScore;

   public static String userName;
   public static int userScore;
   
   
   public static void setOpponentName(String opponentName){
      UserAPI.opponentName = opponentName;
   }
   public static void setUserName(String userName){
      UserAPI.userName = userName;
   }
   public static void setOpponentScore(int opponentScore){
      UserAPI.opponentScore = opponentScore;
   }
   public static void setUserScore(int userScore){
      UserAPI.userScore = userScore;
   }
   
   public static  String getOpponentName(){
      return UserAPI.opponentName;
   }
   public static  String getUserName(){
      return UserAPI.userName;
   }
   public static  int getOpponentScore(){
      return UserAPI.opponentScore;
   }
   public static  int getUserScore(){
      return UserAPI.userScore;
   }
   
}