package version2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// 角色基类，实现序列化
class Role implements Serializable {
    private static final long serialVersionUID = 1L;
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

// 战士类，实现序列化
class Warrior extends Role implements Serializable {
    private static final long serialVersionUID = 2L;
    private String skill;
    private String equipment;

    public Warrior(int hp, int attack) {
        super(hp, attack);
        this.skill = "旋风斩";
        this.equipment = "重型铠甲";
    }

    public String getSkill() { return skill; }
    public String getEquipment() { return equipment; }
    public void setSkill(String skill) { this.skill = skill; }
    public void setEquipment(String equipment) { this.equipment = equipment; }
}

// 法师类，实现序列化
class Mage extends Role implements Serializable {
    private static final long serialVersionUID = 3L;
    private String skill;
    private String equipment;

    public Mage(int hp, int attack) {
        super(hp, attack);
        this.skill = "火球术";
        this.equipment = "魔法长袍";
    }

    public String getSkill() { return skill; }
    public String getEquipment() { return equipment; }
    public void setSkill(String skill) { this.skill = skill; }
    public void setEquipment(String equipment) { this.equipment = equipment; }
}

// 刺客类，实现序列化
class Assassin extends Role implements Serializable {
    private static final long serialVersionUID = 4L;
    private String skill;
    private String equipment;

    public Assassin(int hp, int attack) {
        super(hp, attack);
        this.skill = "背刺";
        this.equipment = "刺客匕首";
    }

    public String getSkill() { return skill; }
    public String getEquipment() { return equipment; }
    public void setSkill(String skill) { this.skill = skill; }
    public void setEquipment(String equipment) { this.equipment = equipment; }
}

public class RoleEditor extends JFrame {
    private JComboBox<String> roleType;
    private JTextField hpField, attackField;
    private JTextArea infoArea;
    private JTextField skillField, equipmentField;

    public RoleEditor() {
        setTitle("游戏角色编辑器");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 顶部选择框
        roleType = new JComboBox<>(new String[]{"战士", "法师", "刺客"});
        roleType.addActionListener(new RoleTypeListener());

        // 中间属性编辑
        JPanel propPanel = new JPanel(new GridLayout(4, 2));
        propPanel.add(new JLabel("生命值：")); hpField = new JTextField(); propPanel.add(hpField);
        propPanel.add(new JLabel("攻击力：")); attackField = new JTextField(); propPanel.add(attackField);
        propPanel.add(new JLabel("技能：")); skillField = new JTextField(); propPanel.add(skillField);
        propPanel.add(new JLabel("装备：")); equipmentField = new JTextField(); propPanel.add(equipmentField);

        // 底部信息展示
        infoArea = new JTextArea();

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        JButton saveBtn = new JButton("保存修改");
        saveBtn.addActionListener(e -> saveRoleProperties());
        JButton saveFileBtn = new JButton("保存到文件");
        saveFileBtn.addActionListener(e -> saveToFile());
        JButton loadFileBtn = new JButton("从文件加载");
        loadFileBtn.addActionListener(e -> loadFromFile());
        buttonPanel.add(saveBtn); buttonPanel.add(saveFileBtn); buttonPanel.add(loadFileBtn);

        add(roleType, BorderLayout.NORTH);
        add(propPanel, BorderLayout.CENTER);
        add(infoArea, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.EAST);
    }

    class RoleTypeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String type = (String) roleType.getSelectedItem();
            Role role;
            switch (type) {
                case "战士": role = new Warrior(100, 20); break;
                case "法师": role = new Mage(80, 30); break;
                case "刺客": role = new Assassin(90, 25); break;
                default: return;
            }
            hpField.setText(String.valueOf(role.getHp()));
            attackField.setText(String.valueOf(role.getAttack()));
            if (role instanceof Warrior) {
                Warrior w = (Warrior) role;
                skillField.setText(w.getSkill());
                equipmentField.setText(w.getEquipment());
            } else if (role instanceof Mage) {
                Mage m = (Mage) role;
                skillField.setText(m.getSkill());
                equipmentField.setText(m.getEquipment());
            } else if (role instanceof Assassin) {
                Assassin a = (Assassin) role;
                skillField.setText(a.getSkill());
                equipmentField.setText(a.getEquipment());
            }
            showRoleInfo(role);
        }
    }

    private void showRoleInfo(Role role) {
        infoArea.setText("");
        if (role instanceof Warrior) {
            Warrior warrior = (Warrior) role;
            infoArea.append("职业：战士\n技能：" + warrior.getSkill() + "\n装备：" + warrior.getEquipment() + "\n");
        } else if (role instanceof Mage) {
            Mage mage = (Mage) role;
            infoArea.append("职业：法师\n技能：" + mage.getSkill() + "\n装备：" + mage.getEquipment() + "\n");
        } else if (role instanceof Assassin) {
            Assassin assassin = (Assassin) role;
            infoArea.append("职业：刺客\n技能：" + assassin.getSkill() + "\n装备：" + assassin.getEquipment() + "\n");
        }
    }

    private void saveRoleProperties() {
        String hpStr = hpField.getText();
        String attackStr = attackField.getText();
        if (!hpStr.matches("\\d+") || !attackStr.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "生命值和攻击力需输入数字！");
            return;
        }
        Role currentRole = getCurrentRole();
        currentRole.setHp(Integer.parseInt(hpStr));
        currentRole.setAttack(Integer.parseInt(attackStr));
        if (currentRole instanceof Warrior) {
            Warrior w = (Warrior) currentRole;
            w.setSkill(skillField.getText());
            w.setEquipment(equipmentField.getText());
        } else if (currentRole instanceof Mage) {
            Mage m = (Mage) currentRole;
            m.setSkill(skillField.getText());
            m.setEquipment(equipmentField.getText());
        } else if (currentRole instanceof Assassin) {
            Assassin a = (Assassin) currentRole;
            a.setSkill(skillField.getText());
            a.setEquipment(equipmentField.getText());
        }
        showRoleInfo(currentRole);
    }

    private Role getCurrentRole() {
        String type = (String) roleType.getSelectedItem();
        switch (type) {
            case "战士":
                return new Warrior(
                    hpField.getText().isEmpty() ? 0 : Integer.parseInt(hpField.getText()),
                    attackField.getText().isEmpty() ? 0 : Integer.parseInt(attackField.getText())
                );
            case "法师":
                return new Mage(
                    hpField.getText().isEmpty() ? 0 : Integer.parseInt(hpField.getText()),
                    attackField.getText().isEmpty() ? 0 : Integer.parseInt(attackField.getText())
                );
            case "刺客":
                return new Assassin(
                    hpField.getText().isEmpty() ? 0 : Integer.parseInt(hpField.getText()),
                    attackField.getText().isEmpty() ? 0 : Integer.parseInt(attackField.getText())
                );
        }
        return null;
    }

    private void saveToFile() {
        Role currentRole = getCurrentRole();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("role_data.dat"))) {
            oos.writeObject(currentRole);
            JOptionPane.showMessageDialog(this, "数据保存成功！");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "保存失败：" + ex.getMessage());
        }
    }

    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("role_data.dat"))) {
            Role role = (Role) ois.readObject();
            if (role instanceof Warrior) {
                roleType.setSelectedItem("战士");
                updateFields(role, (Warrior) role);
            } else if (role instanceof Mage) {
                roleType.setSelectedItem("法师");
                updateFields(role, (Mage) role);
            } else if (role instanceof Assassin) {
                roleType.setSelectedItem("刺客");
                updateFields(role, (Assassin) role);
            }
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "读取失败：" + ex.getMessage());
        }
    }

    private void updateFields(Role role, Object specificRole) {
        hpField.setText(String.valueOf(role.getHp()));
        attackField.setText(String.valueOf(role.getAttack()));
        if (specificRole instanceof Warrior) {
            Warrior w = (Warrior) specificRole;
            skillField.setText(w.getSkill());
            equipmentField.setText(w.getEquipment());
        } else if (specificRole instanceof Mage) {
            Mage m = (Mage) specificRole;
            skillField.setText(m.getSkill());
            equipmentField.setText(m.getEquipment());
        } else if (specificRole instanceof Assassin) {
            Assassin a = (Assassin) specificRole;
            skillField.setText(a.getSkill());
            equipmentField.setText(a.getEquipment());
        }
        showRoleInfo(role);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RoleEditor().setVisible(true));
    }
}