class Key_mana{
  private HashMap<Integer,Boolean> key_flag = new HashMap<Integer,Boolean>();
  Key_mana(){
  }
  
  void flag_on(){
    key_flag.put(keyCode,true);
  }
  
  void flag_off(){
    key_flag.put(keyCode,true);
  }
  
  boolean get_flag(int code){
    return key_flag.get(code);
  }
}
