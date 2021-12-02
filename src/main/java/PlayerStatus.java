import lombok.Getter;
import lombok.Setter;
import weapons.Weapon;

import java.util.Arrays;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static ultils.Util.isPerfect;
import static ultils.Util.isPrimeNumber;
import static weapons.WeaponRange.closeRange;
import static weapons.WeaponRange.longRange;

@Getter
@Setter
public class PlayerStatus {
    private String nickname;
    private int score;
    private int lives;
    private int health;
    private boolean inTheGame = true;
    private String weaponInHand;
    private double positionX;
    private double positionY;
    private static String gameName;

    public PlayerStatus(String nickname) {
        this.nickname = nickname;
    }

    private void addHealth(int healthPoints) {
        if (healthPoints + health < 100) {
            health += healthPoints;
        } else {
            health = 100;
        }
    }

    private void removeHealth(int healthPoints) {
        if (health - healthPoints <= 0) {
            if (lives - 1 <= 0) {
                inTheGame = false;
                System.out.println("Game over");
            } else {
                lives--;
                health = 100;
            }
        } else {
            health -= healthPoints;
        }
    }

    private boolean isValidWeaponInHand(String weapon) {
        return Arrays.stream(Weapon.values()).anyMatch(e -> e.getKey().equals(weapon));
    }

    private boolean canBuyWeapon(String weapon) {
        return Weapon.valueOf(weapon).getValue() <= score;
    }

    private double calculateDistance(PlayerStatus opponent) {
        double opponentX = opponent.getPositionX();
        double opponentY = opponent.getPositionY();

        return sqrt(pow(positionX - opponentX, 2) + pow(positionY - opponentY, 2));
    }

    public boolean isInTheGame() {
        return inTheGame;
    }

    public void initPlayer(String nickname) {
        this.nickname = nickname;
    }

    public void initPlayer(String nickname, int lives) {
        this.nickname = nickname;
        this.lives = lives;
    }

    public void initPlayer(String nickname, int lives, int score) {
        this.nickname = nickname;
        this.lives = lives;
        this.score = score;
    }

    public void findArtifact(int artifactCode) {
        int digitSum = String.valueOf(artifactCode)
                .chars()
                .map(Character::getNumericValue)
                .sum();

        if (isPerfect(artifactCode)) {
            score += 5000;
            lives += 1;
            health = 100;
        } else if (isPrimeNumber(artifactCode)) {
            score += 1000;
            lives += 2;
            addHealth(25);
        } else if (artifactCode % 2 == 0 && digitSum % 3 == 0) {
            score -= 3000;
            removeHealth(25);
        } else {
            score += artifactCode;
        }
    }

    public boolean addWeaponInHand(String weaponInHand) {
        if (isValidWeaponInHand(weaponInHand) && canBuyWeapon(weaponInHand)) {
            score -= Weapon.valueOf(weaponInHand).getValue();
            setWeaponInHand(weaponInHand);
            return true;
        }
        return false;
    }

    public void movePlayerTo(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public boolean shouldAtackOpponent(PlayerStatus opponent) {
        if (opponent.getWeaponInHand().equals(weaponInHand)) {
            return (3 * health + score / 1000) / 4 > (3 * opponent.getHealth() + opponent.getScore() / 1000) / 4;
        } else {
            double distance = calculateDistance(opponent);
            if (distance> 1000){
                return longRange.get(weaponInHand) > longRange.get(opponent.getWeaponInHand());
            }else{
                return closeRange.get(weaponInHand) > closeRange.get(opponent.getWeaponInHand());
            }
        }
    }

}
