import java.util.*;

public class IndivPokemon {
    public int m_dicNum;
    public boolean m_isCustomName;
    public int m_level;
    public int m_exp;
    public int m_maxExp;
    public PokemonTribe m_tribe;
    public TribeValue m_stats;
    public int m_currentHealth;
    public String m_name;

    public IndivPokemon() {
        m_dicNum = 0;
        m_currentHealth = 0;
    }

    public IndivPokemon(int dicNum) {
        m_dicNum = dicNum;
        m_level = 5;
        m_exp = 0;
        m_tribe = pokemonDic.get(dicNum);
        m_maxExp = m_tribe.MaxExpByLevel(m_level);
        m_stats = m_tribe.StatsByLevel(m_level);
        m_currentHealth = m_stats.health;
        m_name = m_tribe.m_name;
        m_isCustomName = false;
    }

    public IndivPokemon(int dicNum, int level) {
        m_dicNum = dicNum;
        m_level = level;
        m_exp = 0;
        m_tribe = pokemonDic.get(dicNum);
        m_maxExp = m_tribe.MaxExpByLevel(m_level);
        m_stats = m_tribe.StatsByLevel(m_level);
        m_currentHealth = m_stats.health;
        m_name = m_tribe.m_name;
        m_isCustomName = false;
    }

    public boolean IsEmpty() {
        return m_dicNum == 0;
    }

    public void GetExp(int exp) {
        if (m_level == 100)
            exp = 0;
        m_exp += exp;
        System.out.printf("%d만큼의 경험치를 얻었다!\n", exp);
        if (m_exp >= m_maxExp)
            LevelUp();
    }
}
