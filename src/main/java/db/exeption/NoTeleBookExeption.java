package db.exeption;

public class NoTeleBookExeption extends RuntimeException {
  private String cityInput;
  public NoTeleBookExeption(String cityInput){
    this.cityInput = cityInput;
  }
  public String getMessage (){
    return ("TeleBook by City - "+ cityInput +
            " doesn't exists. Please choose a new TeleBook.");
  }

}
