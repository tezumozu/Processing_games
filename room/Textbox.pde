class Textbox{
  private String[] text;
  private String font;
  private int count;
  private int text_length;
  private int seen;

// コンストラクタ  
  Textbox(){
    text_length = 1;
    count = 1;
  }
  

  int get_length(){
    return text[seen].length();
  }
  int get_text_length(){
    return text_length;  
  }

//テキストの初期化
  void make(String[] text){
    this.text = text;
    text_length = 1;
    seen = 0;
  }
  
 //テキスト出力
  void draw_text(){
    textAlign(LEFT);
    if(text[seen].length() != 0){
      //枠組み
      fill(0);
      stroke(255);
      strokeWeight(width/100);
      rect(0,height/4*3,width,height/4);
      
      //文字の追加
      if(text_length < text[seen].length()){
        if(count%3 == 0){
          count = 1;
          text_length++;
        }else{
          count++;
        }
      }
      
     //テキスト出力
     if(text_length > text[seen].length()){
       text_length = text[seen].length();
     }
     font = "";
     for(int i = 0; i < text_length; i++){
       font += text[seen].substring(i,i+1);
     }
    fill(255);
    textSize(width/30);
    text(font,20,height/4*3+40);
    }   
  }
 
  //テキスト削除
  boolean remove_text(){
    if(text_length >= text[seen].length()){
      seen++;
      text_length = 1;
    }else{//全て出力されていなければ
      text_length = text[seen].length();
    }
    
    if(seen+1 > text.length){
      seen = 0;
      return true;
    }
    return false;
  }
  
}
