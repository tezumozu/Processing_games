class Deck{
  private Status[] status = new Status[54];
  private Form[] form = new Form[54]; 
  Deck(){
    for(int i = 1; i <= 13; i++){
      
      status[i] = new Status(i,"H");
      form[i] = new Form(width/40*3,53,i);
      status[i+13] = new Status(i,"D");
      form[i+13] = new Form(width/40*3,53,i+13);
      status[i+26] = new Status(i,"C");
      form[i+26] = new Form(width/40*3,53,i+26);
      status[i+39] = new Status(i,"S");
      form[i+39] = new Form(width/40*3,53,i+39);
    }
    
    //joker
    status[53] = new Status(0,"Joker");
    form[53] = new Form(width/40*3,53,53);
  }
  
  public Form[] get_form(){
    return form;
  }
  
  public Status[] get_status(){
    return status;
  }
  
  void deck_shuffle(){
    Status[] copy = new Status[54];
    System.arraycopy(status,1,copy,1,53);
    
    for(int i = 0; i <  53; i++){
     
     int rand =  floor(random(0,1)*(53-i))+1;
     status[i+1] = copy[rand];
     for(int j = rand; j < 53-i; j++){
       copy[j] = copy[j+1];
     }
    }
  }
  
  void draw_deck(){
    for(int i = 1; i <= 53; i++){
      form[i].draw_card(status);
    }
  }
}
