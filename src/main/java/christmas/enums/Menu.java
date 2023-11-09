package christmas.enums;

import christmas.dto.MenuInfo;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum Menu {
    APPETIZER(addMenus("양송이수프", 6000, "타파스", 5500, "시저샐러드", 8000)),
    MAIN(addMenus("티본스테이크", 55000, "바비큐립", 54000, "해산물파스타", 35000, "크리스마스파스타", 25000)),
    DESSERT(addMenus("초코케이크", 15000, "아이스크림", 5000)),
    DRINK(addMenus("제로콜라", 3000, "레드와인", 60000, "샴페인", 25000));

    private final List<MenuInfo> menuInfos;

    Menu(List<MenuInfo> menuItems) {
        this.menuInfos = menuItems;
    }

    public static boolean isDrinkMenu(String name) {
        return DRINK.menuInfos.stream()
                .map(MenuInfo::name)
                .anyMatch(drinkName -> drinkName.equals(name));
    }

    public static boolean isContains(String name) {
        return getMenuNames()
                .stream()
                .anyMatch(menuName -> menuName.equals(name));
    }

    private static List<String> getMenuNames() {
        return EnumSet.allOf(Menu.class).stream()
                .flatMap(menu -> menu.menuInfos.stream())
                .map(MenuInfo::name)
                .collect(Collectors.toList());
    }

    private static List<MenuInfo> addMenus(Object... menus) {
        List<MenuInfo> menuItems = new ArrayList<>();
        for (int i = 0; i < menus.length; i += 2) {
            String name = (String) menus[i];
            int price = (int) menus[i + 1];
            menuItems.add(new MenuInfo(name, price));
        }
        return menuItems;
    }
}
