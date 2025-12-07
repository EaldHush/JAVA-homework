package version1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 角色基类
class Role {
    private int hp;
    private int attack;

    public Role(int hp, int attack) {
        this.hp = hp;
        this.attack = attack;
    }

    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }
    public int getAttack() { return attack; }
    public void setAttack(int attack) { this.attack = attack; }
}

// 战士类
class Warrior extends Role {
    private String skill;
    private String equipment;

    public Warrior(int hp, int attack) {
        super(hp, attack);
        this.skill = "旋风斩";
        this.equipment = "重型铠甲";
    }

    public String getSkill() { return skill; }
    public String getEquipment() { return equipment; }
}

// 法师类
class Mage extends Role {
    private String skill;
    private String equipment;

    public Mage(int hp, int attack) {
        super(hp, attack);
        this.skill = "火球术";
        this.equipment = "魔法长袍";
    }

    public String getSkill() { return skill; }
    public String getEquipment() { return equipment; }
}

// 刺客类
class Assassin extends Role {
    private String skill;
    private String equipment;

    public Assassin(int hp, int attack) {
        super(hp, attack);
        this.skill = "背刺";
        this.equipment = "刺客匕首";
    }

    public String getSkill() { return skill; }
    public String getEquipment() { return equipment; }
}

public class RoleEditor extends JFrame {
    private JComboBox<String> roleType;
    private JTextField hpField, attackField;
    private JTextArea infoArea;

    public RoleEditor() {
        setTitle("游戏角色编辑器");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 顶部选择框
        roleType = new JComboBox<>(new String[]{"战士", "法师", "刺客"});
        roleType.addActionListener(new RoleTypeListener());

        // 中间属性编辑
        JPanel propPanel = new JPanel(new GridLayout(2, 2));
        propPanel.add(new JLabel("生命值："));
        hpField = new JTextField();
        propPanel.add(hpField);
        propPanel.add(new JLabel("攻击力："));
        attackField = new JTextField();
        propPanel.add(attackField);

        // 底部信息展示
        infoArea = new JTextArea();

        add(roleType, BorderLayout.NORTH);
        add(propPanel, BorderLayout.CENTER);
        add(infoArea, BorderLayout.SOUTH);
    }

    class RoleTypeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String type = (String) roleType.getSelectedItem();
            Role role;
            switch (type) {
                case "战士":
                    role = new Warrior(100, 20);
                    break;
                case "法师":
                    role = new Mage(80, 30);
                    break;
                case "刺客":
                    role = new Assassin(90, 25);
                    break;
                default:
                    return;
            }
            hpField.setText(String.valueOf(role.getHp()));
            attackField.setText(String.valueOf(role.getAttack()));
            showRoleInfo(role);
        }
    }

    private void showRoleInfo(Role role) {
        infoArea.setText("");
        if (role instanceof Warrior) {
            Warrior warrior = (Warrior) role;
            infoArea.append("职业：战士\n");
            infoArea.append("技能：" + warrior.getSkill() + "\n");
            infoArea.append("装备：" + warrior.getEquipment() + "\n");
        } else if (role instanceof Mage) {
            Mage mage = (Mage) role;
            infoArea.append("职业：法师\n");
            infoArea.append("技能：" + mage.getSkill() + "\n");
            infoArea.append("装备：" + mage.getEquipment() + "\n");
        } else if (role instanceof Assassin) {
            Assassin assassin = (Assassin) role;
            infoArea.append("职业：刺客\n");
            infoArea.append("技能：" + assassin.getSkill() + "\n");
            infoArea.append("装备：" + assassin.getEquipment() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RoleEditor().setVisible(true));
    }
}