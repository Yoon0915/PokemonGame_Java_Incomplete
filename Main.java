import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final int MAX_HANGING_POKEMON = 6;
    private static Trainer player = new Trainer();

    private static void ShowHangingPokemon() {
        for (int i = 0; i < MAX_HANGING_POKEMON; i++) {
            IndivPokemon p = player.m_hangingPokeList.get(i);
            System.out.printf("%d: ", i + 1);
            if (!p.IsEmpty()) {
                System.out.println("%s [ 전투 %s ]\n", p.m_name, !p.IsDead() ? "가능" : "불능");
                System.out.printf("Lv: %d\n", p.m_level);
                System.out.printf("HP: %d / %d\n", p.m_currentHealth, p.m_stats.health);
            } else
                System.out.println("지정된 포켓몬이 없습니다.\n");
            System.out.println("\n");
        }
    }

    private static void SwapFightPokemon(IndivPokemon fightPokemon) {
        ShowHangingPokemon();
        System.out.println("교체할 포켓몬을 선택해주십시오: ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int choice = scanner.nextInt();
            choice--;
            if (player.m_hangingPokeList.get(choice).IsEmpty())
                System.out.println("지정된 포켓몬이 없습니다. 다시 선택해주세요.\n");
            else if (player.m_hangingPokeList.get(choice).IsDead())
                System.out.println("전투 불능인 포켓몬입니다. 다시 선택해주세요.\n");
            else if (choice == player.m_fightPokemonIndex)
                System.out.println("현재 전투 중인 포켓몬입니다. 다시 선택해주세요.\n");
            else {
                player.m_hangingPokeList.set(player.m_fightPokemonIndex, fightPokemon);
                player.m_fightPokemonIndex = choice;
                fightPokemon = player.m_hangingPokeList.get(choice);
                break;
            }
        }
    }

    int MyAttackAndCheckWhileCaptureMode(IndivPokemon fightPokemon, IndivPokemon enemyPokemon) {
        System.out.printf("가랏 %s!!! 공격해!!!\n", fightPokemon.m_name);
        fightPokemon.Attack(enemyPokemon);
        if (enemyPokemon.m_currentHealth <= 0) {
            enemyPokemon.m_currentHealth = 0;
            System.out.printf("%s를 물리쳤다\n", enemyPokemon.m_tribe.m_name);
            int getExp = enemyPokemon.m_tribe.m_baseExpYield * enemyPokemon.m_level / 7;
            fightPokemon.GetExp(getExp);
            player.m_hangingPokeList[player.m_fightPokemonIndex] = fightPokemon;
            return 0;
        }
        return 1;
    }

    int EnemyAttackAndCheckWhileCaptureMode(IndivPokemon fightPokemon, IndivPokemon enemyPokemon) {
        System.out.printf("적 %s(이)가 %s(을)를 공격했다..!\n", enemyPokemon.m_tribe.m_name, fightPokemon.m_name);
        enemyPokemon.Attack(fightPokemon);
        if (fightPokemon.m_currentHealth <= 0) {
            fightPokemon.m_currentHealth = 0;
            player.m_hangingPokeList[player.m_fightPokemonIndex] = fightPokemon;
            System.out.printf("%s(이)가 쓰러졌다..\n", fightPokemon.m_name);

            if (player.IsAllDead()) {
                System.out.printf("더 이상 싸울 수 있는 포켓몬이 없다..\n");
                System.out.printf("당신은 눈 앞이 깜깜해져 정신 없이 도망치며 포켓몬 센터로 향했다...\n");
                return 0;
            }
            System.out.printf("포켓몬을 교체하시겠습니까?\n");
            System.out.printf("1. 교체\n");
            System.out.printf("2. 도망\n");
            if (fightPokemon.m_currentHealth <= 0) {
                fightPokemon.m_currentHealth = 0;
                player.m_hangingPokeList[player.m_fightPokemonIndex] = fightPokemon;
                System.out.printf("%s(이)가 쓰러졌다..\n", fightPokemon.m_name);
                if (player.IsAllDead()) {
                    System.out.printf("더 이상 싸울 수 있는 포켓몬이 없다..\n");
                    System.out.printf("당신은 눈 앞이 깜깜해져 정신 없이 도망치며 포켓몬 센터로 향했다...\n");
                    return 0;
                }
                System.out.printf("포켓몬을 교체하시겠습니까?\n");
                System.out.printf("1. 교체\n");
                System.out.printf("2. 도망\n");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        SwapFightPokemon(fightPokemon);
                        return 2;
                    break;
                    case 2:
                        System.out.printf("당신은 비겁하게 도망쳤습니다..\n");
                        player.m_hangingPokeList[player.m_fightPokemonIndex] = fightPokemon;
                        return 0;
                    break;
                }
                scanner.close();
            }
        }
        return 1;
    }

    void captureMode() {
        Random rd = new Random();
        int probOfOccur = rd.nextInt(NUMBER_OF_POKEMON) + 1;
        IndivPokemon fightPokemon = player.m_hangingPokeList[player.m_fightPokemonIndex];
        int enemyPokemonLevel = fightPokemon.m_level - 2;
        IndivPokemon enemyPokemon = new IndivPokemon(enemyPokemonDicNum, enemyPokemonLevel);
        System.out.printf("야생의 %s(이)가 나타났다!\n", enemyPokemon.m_name);
        System.out.printf("가랏 %s!!\n", fightPokemon.m_name);
        int captureModeNum = 1;
        while (captureModeNum != 0) {
            System.out.printf("상대 %s의 HP: %d / %d\n", enemyPokemon.m_tribe.m_name, enemyPokemon.m_currentHealth, enemyPokemon.m_stats.health);
            System.out.printf("내 %s의 HP: %d / %d\n", fightPokemon.m_name, fightPokemon.m_currentHealth, fightPokemon.m_stats.health);
            System.out.println("1. 공격");
            System.out.println("2. 가방");
            System.out.println("3. 도망");
            System.out.println("4. 교체");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    boolean isFirstAttack = fightPokemon.m_stats.speed >= enemyPokemon.m_stats.speed;
                    if (isFirstAttack) {
                        captureModeNum = MyAttackAndCheckWhileCaptureMode(fightPokemon, enemyPokemon);
                        if (captureModeNum == 0)
                            break;
                    }
                    captureModeNum = EnemyAttackAndCheckWhileCaptureMode(fightPokemon, enemyPokemon);
                    if (captureModeNum == 0)
                        break;
                    if (captureModeNum == 2) {
                        captureModeNum = 1;
                        break;
                    }
                    if (!isFirstAttack) {
                        captureModeNum = MyAttackAndCheckWhileCaptureMode(fightPokemon, enemyPokemon);
                        if (captureModeNum == 0)
                            break;
                    }
                    break;
                }

                case 2:
                {
                    boolean isOpeningBag = true;
                    while (isOpeningBag)
                    {
                        int choice = player.OpenBag();
                        if (choice == 0)
                            break;

                        if (choice == 1)
                        {
                            boolean isSelecting = true;
                            while (isSelecting)
                            {
                                player.ShowMonsterBallList();
                                System.out.print("어떤 볼을 던지겠습니까?(뒤로가기: 0): ");
                                int ballIdx = scanner.nextInt();
                                if (ballIdx == 0)
                                    break;
                                ballIdx--;
                                if (player.m_balls[ballIdx].m_count <= 0)
                                {
                                    System.out.println("볼의 개수가 부족합니다. 다시 선택하십시오.");
                                    continue;
                                }
                                player.ThrowMonsterBall(ballIdx, enemyPokemon);
                                isOpeningBag = false;
                                captureModeNum = 0;
                                break;
                            }
                        }

                        if (choice == 2)
                        {
                            boolean isSelecting = true;
                            while (isSelecting)
                            {
                                player.ShowMonsterPotionList();
                                System.out.print("어떤 포션을 먹이겠습니까?(뒤로가기: 0): ");
                                int potionIdx = scanner.nextInt();
                                if (potionIdx == 0)
                                    break;
                                potionIdx--;
                                if (player.m_potions[potionIdx].m_count <= 0)
                                {
                                    System.out.println("포션의 개수가 부족합니다. 다시 선택하십시오.");
                                    continue;
                                }
                                player.GivePotion(potionIdx, fightPokemon);
                                captureModeNum = EnemyAttackAndCheckWhileCaptureMode(fightPokemon, enemyPokemon);
                                isOpeningBag = false;
                                break;
                            }
                        }
                    }
                    break;
                }

                case 3:
                    System.out.println("당신은 비겁하게 도망쳤습니다..\n");
                    player.m_hangingPokeList[player.m_fightPokemonIndex] = fightPokemon;
                    captureModeNum = 0;
                    break;

                case 4:
                    SwapFightPokemon(fightPokemon);
                    captureModeNum = EnemyAttackAndCheckWhileCaptureMode(fightPokemon, enemyPokemon);
                    break;
            }
            scanner.close();
        }
    }
}

    public static void main(String[] args) {
        LoadFile();
        Gaming();
        SaveFile();
    }

    public static void LoadFile() {
        // 만약 게임을 처음 실행한다면(= 세이브파일이 존재하지 않고)  포켓몬 선택 실행
        if (player.IsAllDead()) {
            System.out.println("오박사: 무슨 포켓몬을 얻고 싶니?");
            System.out.println("1. 이상해씨");
            System.out.println("2. 파이리");
            System.out.println("3. 꼬부기");

            int choice = input.nextInt();
            player.TakeNewPokemon((choice - 1) * 3 + 1);
        }
    }

    public static void Gaming() {
        while (true) {
            System.out.println("1. 대전모드");
            System.out.println("2. 수집모드");
            System.out.println("3. 가방보기");
            System.out.println("4. 포켓몬 센터 가기");
            System.out.println("0. 게임 종료");

            int choice = input.nextInt();

            switch (choice) {
                // 대전모드(미구현)
                case 1: {

                }
                // 쌉구현
                case 2: {
                    CaptureMode();
                    break;
                }
                //여기에 맞는 OpenBag()가 아님 추후 구현
                case 3: {
                    // player.OpenBag();
                    break;
                }

                case 4: {
                    player.GotoPokemonCenter();
                    break;
                }
                case 0: {
                    System.out.println("게임을 종료합니다.");
                    return;
                }
            }
        }
    }

    public static void SaveFile() {

    }

    public static void CaptureMode() {
        // 구현되지 않음
    }
}
