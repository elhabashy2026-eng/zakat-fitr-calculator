package ui;

public abstract class Screen {
    
    public abstract void show();

    protected void displayHeader(String title) {
        line('='); 
        System.out.println(center(title)); 
        line('='); 
        System.out.println(); 
    }
    
    private void line(char c) {
        int totalWidth = 50; 
        System.out.println(String.valueOf(c).repeat(totalWidth));
    }

    private String center(String title) {
        String formattedTitle = title.trim().toUpperCase();
        int totalWidth = 50;
        int titleLength = formattedTitle.length();
        
        if (titleLength >= totalWidth) {
            return formattedTitle.substring(0, totalWidth); 
        }

        int totalPaddingNeeded = totalWidth - titleLength;
        int leftPaddingSize = totalPaddingNeeded / 2;
        int rightPaddingSize = totalPaddingNeeded - leftPaddingSize;

        String leftPadding = " ".repeat(leftPaddingSize);
        String rightPadding = " ".repeat(rightPaddingSize);

        return leftPadding + formattedTitle + rightPadding;
    }
}
