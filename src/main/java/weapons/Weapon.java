package weapons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Weapon {
    KNIFE("knife", 1000),
    SNIPER("sniper", 10000),
    KALASHNIKOV("kalashnikov", 20000);

    private final String key;
    private final int value;
}
