package Pokemon;
import java.util.Scanner;

public class BattleModeTrainer {
    private boolean isMyTurn = false;

    void BattlePhase()
    {
        if (mySpeed > ememySpeed)
        {
            isMyTurn = true;
        }

        pokemonArray myPokemon =  bagPokemonArray[0];
        System.out.println("내 포켓몬 :" + myPokemon.getName() +
                " 체력 :" + myPokemon.getHP() + "/" + myPokemon.maxHP());

        //myPokemon 나중에 배열 값으로 수정 필요

        while (isMyTurn == true)
        {
            System.out.println("나의 턴");
            System.out.println("수행할 동작을 번호로 입력하세요.");
            System.out.println("");
            System.out.println("----------------");
            System.out.println("1. 공격   2. 교체");
            System.out.println("3. 포기   4. 가방");
            System.out.println("----------------");

            Scanner chooseNum = new Scanner(System.in);

            String choice = chooseNum.next();

            while (true)
            {
                switch (choice)
                {
                    case "1":
                        System.out.println("상대 포켓몬을 공격합니다.");
                        WildPokemon.getHP() - Attack.calculateDamage();
                        System.out.println("상대 포켓몬에게 " + Attack.calculateDamage() +
                                "의 데미지를 입혔습니다.");
                        System.out.println("턴을 종료합니다.");
                        endTurn();
                        break;

                    case "2":
                        System.out.println("교체할 포켓몬을 1~5번호로 입력해주세요.");
                        Scanner shiftNum = new Scanner(System.in);
                        String choice_2 = shiftNum.next();

                        // 입력된 번호가 1~5 사이의 값인지 검사
                        if (!choice_2.matches("[1-5]"))
                        {
                            System.out.println("잘못된 번호를 입력하셨습니다. 번호를 다시 입력해주세요.");
                            continue; // 다시 번호를 입력받도록 continue 문을 사용
                        }

                        // [0]에 있는 포켓몬을 [6]으로 이동 후 [choice_2]에 있는 포켓몬을 [0]으로 이동
                        bagPokemonArray[6] = bagPokemonArray[0];
                        bagPokemonArray[0] = bagPokemonArray[Integer.parseInt(choice_2)];

                        // [6]에 있는 포켓몬을 [choice_2]로 이동
                        bagPokemonArray[Integer.parseInt(choice_2)] = bagPokemonArray[6];
                        endTurn();
                        break;

                    case "3":
                        System.out.println("대전을 포기하고 도망갑니다.");
                        //gameStartPage로 돌아감
                        break;

                    case "4":
                        System.out.println("가방을 엽니다.");
                        boolean usedPotion = false;
                        boolean usedPokeball = false;
                        for (Item item : bag.myItem) {
                            if (item instanceof Potion) {
                                Potion potion = (Potion) item;
                                System.out.println(potion.name + ": " + potion.quantity);
                                System.out.println("회복약을 사용하시겠습니까? (Y/N)");
                                String input = scanner.nextLine();
                                if (input.equals("Y")) {
                                    HP += potion.value;
                                    if (HP > maxHP) {
                                        HP = maxHP;
                                    }
                                    potion.quantity--;
                                    if (potion.quantity == 0) {
                                        bag.myItem.remove(potion);
                                    }
                                    usedPotion = true;
                                    break;
                                }
                            } else if (item instanceof Pokeball) {
                                Pokeball pokeball = (Pokeball) item;
                                System.out.println(pokeball.name + ": " + pokeball.quantity);
                                System.out.println("포켓볼을 사용하여 야생 포켓몬을 포획하시겠습니까? (Y/N)");
                                String input = scanner.nextLine();
                                if (input.equals("Y")) {
                                    if (enemy instanceof EnemyTrainerBattle) {
                                        System.out.println("사용할 수 없는 기능입니다!");
                                        break;
                                    } else {
                                        double catchRate = 0.5 * (wildPokemonLevel / 100.0) * (maxHP / (double) wildPokemonHP);
                                        if (new Random().nextDouble() < catchRate) {
                                            System.out.println(wildPokemonName + "을(를) 성공적으로 포획했습니다!");
                                            Trainer.addMyPokemon(new Pokemon(wildPokemonName, wildPokemonLevel, maxHP));
                                        } else {
                                            System.out.println(wildPokemonName + "이(가) 몬스터볼에서 탈출했습니다!");
                                        }
                                        pokeball.quantity--;
                                        if (pokeball.quantity == 0) {
                                            bag.myItem.remove(pokeball);
                                        }
                                        usedPokeball = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if (!usedPotion && !usedPokeball) {
                            System.out.println("아이템 사용을 취소합니다.");
                        }

                        endTurn();
                        break;

                    default:
                        System.out.println("잘못된 번호를 입력하셨습니다 번호를 다시 입력해주세요.");
                        continue;
                }

                if (choice == "3")
                {
                    break;
                }
            }
            chooseNum.close();
            endTurn();
            break;
        }

        if (true)
        {
            GameStartPage gameStartPage = new GameStartPage();
            gameStartPage.StartPage();
        }

        if(myPokemon.getHP() == 0)
        {
            System.out.println("당신의 포켓몬의 HP가 0이 되었습니다.");
            System.out.println("배틀을 종료하고, 시작 페이지로 돌아갑니다.");
            GameStartPage gameStartPage = new GameStartPage();
            gameStartPage.StartPage();
        }
    }

    private void endTurn()
    {
        isMyTurn = false;
    }
}

