package Pokemons;
import java.util.Scanner;
public class GameStartPage
{
    public void StartPage()
    {
        Trainer trainer = new Trainer();
        int level = trainer.level;
        System.out.println("현재 트레이너의 레벨은 : " + trainer.level + "입니다.");
        System.out.println("---------------------");
        System.out.println("1. 대전모드	2. 포획모드");
        System.out.println("3. 가   방 	4. 상   점");
        System.out.println("---------------------");
        System.out.println("");
        System.out.println("수행할 동작을 번호로 입력해주세요");

        while(true)
        {
            Scanner Mode = new Scanner(System.in);
            String ChooseMode = Mode.next();

            switch(ChooseMode)
            {
                case "1" :
                    System.out.println("대전모드를 실행합니다.");

                    break;

                case "2" :
                    System.out.println("포획모드를 실행합니다.");
                    WildPokemonBattle obj = new WildPokemonBattle();
                    obj.WildBattlePhase();
                    break;

                case "3" :
                    System.out.println("가방을 열어 내용물을 확인합니다.");

                    break;

                case "4" :
                    System.out.println("상점으로 들어갑니다.");

                    break;

                    default:
                    System.out.println("잘못된 번호를 입력하셨습니다 번호를 다시 입력해주세요.");
                    continue;
            }

            Mode.close();
            break;
        }

    }
}
