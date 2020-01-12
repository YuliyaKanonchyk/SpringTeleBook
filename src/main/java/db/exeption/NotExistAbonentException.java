package db.exeption;

public class NotExistAbonentException extends RuntimeException {
  private int abonentId;
  public NotExistAbonentException(int abonentId){
    this.abonentId = abonentId;

  }
  public String getMessage (){
    return ("Abonent with id: - "+ abonentId +
            " doesn't exists. Please choose a new Abonent.");
  }

}
