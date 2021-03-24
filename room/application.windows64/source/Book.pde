class Book extends Area{
  private Item book;
  private Textbox textbox = new Textbox();
  private String[] text;
  private int seen;
  
  Book(String url,String book_name){
   seen = 0;
   book  = new Item(width/2,height/2,80,loadImage(url));
    if(book_name == "arisu"){
      text = new String[5];
      text[0] = "〜不思議の国のアリス〜";
      text[1] = "1章　白いうさぎ";
      text[2] = "2章　不思議の国";
      text[3] = "３章　終わらないお茶会";
      text[4] = "４章　ハートの女王";
    }else{
      text = new String[4];
      text[0] = "";
      text[1] = "";
      text[2] = "";
      text[3] = "";
    }
    textbox.make(text);
  }
  
  void draw_area(){
      background(0);
      book.draw_image();
      textbox.draw_text();
  }
  
  void mouse_pres(){
    
    if(textbox.remove_text()){
        c_area = "Books";
    }
  }
  
}
