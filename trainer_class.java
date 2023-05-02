import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Trainer {
    private class Potion {
        String m_name;
        int m_healValue;
        int m_count;
        int m_cost;

        Potion(String name, int healValue, int count, int cost) {
            m_name = name;
            m_healValue = healValue;
            m_count = count;
            m_cost = cost;
        }
    }

    private class MonsterBall {
        String m_name;
        float m_catchRate;
        int m_count;
        int m_cost;

        MonsterBall(String name, float catchRate, int count, int cost) {
            m_name = name;
            m_catchRate = catchRate;
            m_count = count;
            m_cost = cost;
        }
    }

    public IndivPokemon[] m_hangingPokeList = new IndivPokemon[MAX_HANGING_POKEMON];
    public int m_fightPokemonIndex;
    public ArrayList<IndivPokemon> m_pokemonCenterList = new ArrayList<>();
    public int m_hangingPokeCnt;
    public Potion[] m_potions = {
            new Potion("상처약", 20, 5, 200),
            new Potion("좋은상처약", 60, 0, 700),
            new Potion("고급상처약", 120, 0, 1500),
            new Potion("풀회복약", -1, 0, 2500)
    };
    public MonsterBall[] m_balls = {
            new MonsterBall("몬스터볼", 1.0F, 5, 200),
            new MonsterBall("슈퍼볼", 1.5F, 0, 600),
            new MonsterBall("하이퍼볼", 2.0F, 0, 1500),
            new MonsterBall("마스터볼", 255, 0, 2500)
    };
    public int m_candys;

    public Trainer() {
        Arrays.fill(m_hangingPokeList, null);
        m_candys = 0;
        m_hangingPokeCnt = 0;
        m_fightPokemonIndex = 0;
    }

    public boolean IsAllDead() {
        for (int i = 0; i < m_hangingPokeCnt; i++) {
            if (m_hangingPokeList[i].m_currentHealth > 0)
                return false;
        }
        return true;
    }

    void TakeNewPokemon(int dicNum, int level) {
        IndivPokemon newPokemon = new IndivPokemon(dicNum, level);
        System.out.printf("%s!!! 넌 내꺼야!!!\n", newPokemon.GetName());
        if (m_hangingPokeCnt == MAX_HANGING_POKEMON) {
            System.out.printf("들고 다닐 수 있는 포켓몬의 수를 넘어 %s(을)를 포켓몬 센터로 전송합니다.\n", newPokemon.GetName());
            m_pokemonCenterList.add(newPokemon);
            return;
        }
        m_hangingPokeList[m_hangingPokeCnt++] = newPokemon;
    }

    void TakeNewPokemon(IndivPokemon newPokemon) {
        System.out.printf("%s!!! 넌 내꺼야!!!\n", newPokemon.GetName());
        if (m_hangingPokeCnt == MAX_HANGING_POKEMON) {
            System.out.printf("들고 다닐 수 있는 포켓몬의 수를 넘어 %s(을)를 포켓몬 센터로 전송합니다.\n", newPokemon.GetName());
            m_pokemonCenterList.add(newPokemon);
            return;
        }
        m_hangingPokeList[m_hangingPokeCnt++] = newPokemon;
    }

    void PrintHangingPokemonList() {
        System.out.println("현재 데리고 있는 포켓몬: ");
        for (int i = 0; i < MAX_HANGING_POKEMON; i++) {
            System.out.println(m_hangingPokeList[i]);
        }
    }

    void PrintPokemonCenterList() {
        int idx = 1;
        for (IndivPokemon indivPokemon : m_pokemonCenterList) {
            System.out.printf("[%d]\n", idx++);
            indivPokemon.PrintThisPokemonDetailInfo();
        }
    }

    void GotoPokemonCenter() {
        boolean isInPokemonCenter = true;
        while (isInPokemonCenter) {

            Scanner choice_num = new Scanner(System.in);
            int choice = choice_num.nextInt();

            System.out.println("1. 포켓몬 회복");
            System.out.println("2. 포켓몬PC 보기");
            System.out.println("3. 포켓몬 센터 나가기");

            System.out.println("선택하십시오: ");

            switch (choice) {
                case 1: {
                    System.out.println("\n포켓몬을 회복합니다.");
                    System.out.println("띵-띵- 띠로링~!");
                    for (int i = 0; i < MAX_HANGING_POKEMON; i++) {
                        m_hangingPokeList[i].FullRestore();
                    }
                    System.out.println("회복이 완료되었습니다\n");
                    break;
                }

                case 2: {
                    System.out.println("\n포켓몬PC를 열었다.");
                    PrintPokemonCenterList();

                    System.out.print("포켓몬을 교체하시겠습니까?(y/n): ");

                    Scanner choice_char = new Scanner(System.in);
                    char yn = choice_char.next().charAt(0);

                    if (yn == 'y' || yn == 'Y') {
                        int idx = 1;
                        for (IndivPokemon indivPokemon : m_hangingPokeList) {
                            System.out.printf("[%d]%n", idx++);
                            indivPokemon.PrintThisPokemonDetailInfo();
                        }


                        System.out.println("\n교체할 두 포켓몬의 인덱스를 입력하십시오.");
                        System.out.print("포켓몬 센터에 있는 포켓몬의 인덱스: ");

                        Scanner center_num = new Scanner(System.in);
                        int centerIdx = center_num.nextInt();
                        centerIdx--;
                        System.out.print("가지고 있는 포켓몬의 인덱스: ");
                        int hangingIdx = center_num.nextInt();
                        hangingIdx--;

                        m_hangingPokeList[hangingIdx].FullRestore();
                        IndivPokemon temp = m_hangingPokeList[hangingIdx];
                        m_hangingPokeList[hangingIdx] = m_pokemonCenterList[centerIdx];
                        m_pokemonCenterList[centerIdx] = temp;

                        System.out.println("포켓몬을 교환하였습니다.");
                        center_num.close();

                    }
                    choice_char.close();

                    System.out.println("포켓몬PC를 종료합니다.");
                    break;
                }

                case 3:
                    System.out.println("포켓몬 센터에서 나갔다.\n\n");
                    isInPokemonCenter = false;
                    break;
            }
            choice_num.close();
        }
    }

    void ShowMonsterBallList() {
        int idx = 1;
        for (MonsterBall ball : m_balls) {
            System.out.printf("%d %s: %d개\n", idx++, ball.m_name, ball.m_count);
        }
    }

    boolean IsCatchSuccess(int idx, IndivPokemon caughtPokemon) {
        int a = (3 * caughtPokemon.m_stats.health - 2 * caughtPokemon.m_currentHealth) * caughtPokemon.m_tribe.m_catchRate * m_balls[idx].m_catchRate / (3 * caughtPokemon.m_stats.health);
        int b = (int) (65535 * Math.pow(Math.pow((double) a / 255, 0.25), 4) + 0.5F); // 65535는 포켓몬에서 만든 랜덤 상수임.
        Random rand = new Random();
        for (int i = 0; i < 3; i++) { // 4번 돌려서 하나라도 b보다 큰 값이 나오면 실패라 한다
            System.out.println("띵-");
            if (rand.nextInt(65536) > b)
                return false;
        }
        return rand.nextInt(65536) <= b;
    }

    void ThrowMonsterBall(int idx, IndivPokemon caughtPokemon) {
        System.out.println("가랏! 몬스터~볼~~!!!");
        m_balls[idx].m_count--;
        if (IsCatchSuccess(idx, caughtPokemon)) {
            System.out.println("찰칵!");
            TakeNewPokemon(caughtPokemon);
        } else {
            System.out.println("펑!");
            System.out.println("아 안돼..!");
            System.out.printf("%s(은)는 도망쳐 버렸다.\n", caughtPokemon.m_tribe.m_name);
        }
    }

    void showMonsterPotionList() {
        int idx = 1;
        for (Potion potion : m_potions) {
            System.out.printf("%d %s: %d개\n", idx++, potion.m_name, potion.m_count);
        }
    }

    void GivePotion(int idx, IndivPokemon&drinkingPokemon) {
        m_potions[idx].m_count--;
        drinkingPokemon.m_currentHealth += m_potions[idx].m_healValue;
        System.out.printf("%s의 피가 %d만큼 찼다.\n\n", drinkingPokemon.m_name, m_potions[idx].m_healValue);
        if (drinkingPokemon.m_currentHealth > drinkingPokemon.m_stats.health) {
            drinkingPokemon.FullRestore(); // 그냥 풀피가 되라.
        }
    }

    public int openBag() {
        System.out.println("1. 몬스터볼");
        System.out.println("2. 회복약");
        System.out.println("3. 캔디");
        System.out.println("0. 가방 닫기");
        System.out.print("선택하십시오: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return choice;
    }
}
