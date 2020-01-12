package db.exeption;

public class DuplicateAbonentException extends RuntimeException {
  private int abonentId;
  public DuplicateAbonentException(int abonentId){
    this.abonentId = abonentId;

  }
  public String getMessage (){
    return ("Abonent with id: - "+ abonentId +
            " already exists. Please create a new Abonent.");
  }

}
