package menus;

public enum Menu {
    ENCRYPT("Encryption", new EncryptMenu()),
    HASH("Hashing", new HashMenu());

    private String menuName;
    private IMenu menu;
    private Menu(String menuName, IMenu menu) {
        this.menuName = menuName;
        this.menu = menu;
    }

    public String getMenuName() {return menuName;}
    public IMenu getMenu() {return menu;}
}