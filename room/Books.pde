class Books extends Area{
  private Item bookA;
  private Item bookB;
  private Item back;
  Books(){
    bookA = new Item(width/4*3,height/2,70,loadImage("foto/arisu.PNG"));
    bookB = new Item(width/4,height/2,70,loadImage("foto/tea.PNG"));
    back = new Item(width/2,height/32*30,8,loadImage("foto/back.PNG"));
  }
  
  void draw_area(){
    background(0);
    bookA.draw_image();
    bookB.draw_image();
    back.draw_image();
  }
  
  void mouse_pres(){
    if(bookA.check_click()){
      c_area = "Book_arisu";
    }else if(bookB.check_click()){
      c_area = "Book_tea";
    }else if(back.check_click()){
      c_area = "Front";
    }
  }
}
