public class Main {
   public static void main(String[] argv) {
      JFrame frame = new JFrame();
      frame.getContentPane().add(new MyComponent());
      frame.setSize(300, 200);
      frame.setVisible(true);
   }
}