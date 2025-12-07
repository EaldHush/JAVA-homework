package version3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// 角色基类，实现序列化接口，以便可以将角色对象保存到文件中
class Role implements Serializable {
    // 序列化版本号，确保序列化和反序列化时类的版本一致
    private static final long serialVersionUID = 1L;
    // 角色的生命值
    private int hp;
    // 角色的攻击力
    private int attack;

    // 构造方法，用于初始化角色的生命值和攻击力
    public Role(int hp, int attack) {
        this.hp = hp;
        this.attack = attack;
    }

    // 获取角色的生命值
    public int getHp() {
        return hp;
    }

    // 设置角色的生命值
    public void setHp(int hp) {
        this.hp = hp;
    }

    // 获取角色的攻击力
    public int getAttack() {
        return attack;
    }

    // 设置角色的攻击力
    public void setAttack(int attack) {
        this.attack = attack;
    }
}

// 战士类，继承自角色基类，同样实现序列化接口
class Warrior extends Role implements Serializable {
    // 序列化版本号
    private static final long serialVersionUID = 2L;
    // 战士的技能
    private String skill;
    // 战士的装备
    private String equipment;

    // 构造方法，初始化战士的生命值、攻击力、技能和装备
    public Warrior(int hp, int attack) {
        super(hp, attack);
        this.skill = "旋风斩";
        this.equipment = "重型铠甲";
    }

    // 获取战士的技能
    public String getSkill() {
        return skill;
    }

    // 获取战士的装备
    public String getEquipment() {
        return equipment;
    }

    // 设置战士的技能
    public void setSkill(String skill) {
        this.skill = skill;
    }

    // 设置战士的装备
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}

// 法师类，继承自角色基类，实现序列化接口
class Mage extends Role implements Serializable {
    // 序列化版本号
    private static final long serialVersionUID = 3L;
    // 法师的技能
    private String skill;
    // 法师的装备
    private String equipment;

    // 构造方法，初始化法师的生命值、攻击力、技能和装备
    public Mage(int hp, int attack) {
        super(hp, attack);
        this.skill = "火球术";
        this.equipment = "魔法长袍";
    }

    // 获取法师的技能
    public String getSkill() {
        return skill;
    }

    // 获取法师的装备
    public String getEquipment() {
        return equipment;
    }

    // 设置法师的技能
    public void setSkill(String skill) {
        this.skill = skill;
    }

    // 设置法师的装备
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}

// 刺客类，继承自角色基类，实现序列化接口
class Assassin extends Role implements Serializable {
    // 序列化版本号
    private static final long serialVersionUID = 4L;
    // 刺客的技能
    private String skill;
    // 刺客的装备
    private String equipment;

    // 构造方法，初始化刺客的生命值、攻击力、技能和装备
    public Assassin(int hp, int attack) {
        super(hp, attack);
        this.skill = "背刺";
        this.equipment = "刺客匕首";
    }

    // 获取刺客的技能
    public String getSkill() {
        return skill;
    }

    // 获取刺客的装备
    public String getEquipment() {
        return equipment;
    }

    // 设置刺客的技能
    public void setSkill(String skill) {
        this.skill = skill;
    }

    // 设置刺客的装备
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}

// 游戏角色编辑器类，继承自 JFrame 用于创建窗口
public class RoleEditor extends JFrame {
    // 角色类型选择框
    private JComboBox<String> roleType;
    // 生命值输入框
    private JTextField hpField;
    // 攻击力输入框
    private JTextField attackField;
    // 信息显示区域
    private JTextArea infoArea;
    // 技能输入框
    private JTextField skillField;
    // 装备输入框
    private JTextField equipmentField;

    // 构造方法，初始化窗口和组件
    public RoleEditor() {
        // 设置窗口标题
        setTitle("游戏角色编辑器");
        // 设置窗口大小
        setSize(500, 400);
        // 设置窗口关闭时的操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口的布局管理器
        setLayout(new BorderLayout());

        // 初始化角色类型选择框，提供战士、法师、刺客三种选项
        roleType = new JComboBox<>(new String[]{"战士", "法师", "刺客"});
        // 为选择框添加事件监听器
        roleType.addActionListener(new RoleTypeListener());

        // 创建中间的属性编辑面板，使用网格布局
        JPanel propPanel = new JPanel(new GridLayout(4, 2));
        // 添加生命值标签和输入框
        propPanel.add(new JLabel("生命值："));
        hpField = new JTextField();
        propPanel.add(hpField);
        // 添加攻击力标签和输入框
        propPanel.add(new JLabel("攻击力："));
        attackField = new JTextField();
        propPanel.add(attackField);
        // 添加技能标签和输入框
        propPanel.add(new JLabel("技能："));
        skillField = new JTextField();
        propPanel.add(skillField);
        // 添加装备标签和输入框
        propPanel.add(new JLabel("装备："));
        equipmentField = new JTextField();
        propPanel.add(equipmentField);

        // 初始化信息显示区域
        infoArea = new JTextArea();

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        // 创建保存修改按钮
        JButton saveBtn = new JButton("保存修改");
        // 为保存修改按钮添加事件监听器
        saveBtn.addActionListener(e -> saveRoleProperties());
        // 创建保存到文件按钮
        JButton saveFileBtn = new JButton("保存到文件");
        // 为保存到文件按钮添加事件监听器
        saveFileBtn.addActionListener(e -> saveToFile());
        // 创建从文件加载按钮
        JButton loadFileBtn = new JButton("从文件加载");
        // 为从文件加载按钮添加事件监听器
        loadFileBtn.addActionListener(e -> loadFromFile());
        // 将按钮添加到按钮面板
        buttonPanel.add(saveBtn);
        buttonPanel.add(saveFileBtn);
        buttonPanel.add(loadFileBtn);

        // 将角色类型选择框添加到窗口顶部
        add(roleType, BorderLayout.NORTH);
        // 将属性编辑面板添加到窗口中间
        add(propPanel, BorderLayout.CENTER);
        // 将信息显示区域添加到窗口底部
        add(infoArea, BorderLayout.SOUTH);
        // 将按钮面板添加到窗口右侧
        add(buttonPanel, BorderLayout.EAST);
    }

    // 角色类型选择事件监听器类
    class RoleTypeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 获取选择的角色类型
            String type = (String) roleType.getSelectedItem();
            Role role;
            // 根据选择的角色类型创建相应的角色对象
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
            // 将角色的生命值和攻击力显示到输入框中
            hpField.setText(String.valueOf(role.getHp()));
            attackField.setText(String.valueOf(role.getAttack()));
            // 根据角色类型，将技能和装备显示到输入框中
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
            // 显示角色信息
            showRoleInfo(role);
        }
    }

    // 显示角色信息的方法
    private void showRoleInfo(Role role) {
        // 清空信息显示区域
        infoArea.setText("");
        // 根据角色类型，显示角色的职业、技能和装备信息
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

    // 保存角色属性的方法
    private void saveRoleProperties() {
        // 获取生命值输入框的内容
        String hpStr = hpField.getText();
        // 获取攻击力输入框的内容
        String attackStr = attackField.getText();
        // 检查输入的生命值和攻击力是否为数字
        if (!hpStr.matches("\\d+") || !attackStr.matches("\\d+")) {
            // 如果不是数字，弹出提示框
            JOptionPane.showMessageDialog(this, "生命值和攻击力需输入数字！");
            return;
        }
        // 获取当前选择的角色对象
        Role currentRole = getCurrentRole();
        // 设置角色的生命值
        currentRole.setHp(Integer.parseInt(hpStr));
        // 设置角色的攻击力
        currentRole.setAttack(Integer.parseInt(attackStr));
        // 根据角色类型，设置角色的技能和装备
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
        // 显示更新后的角色信息
        showRoleInfo(currentRole);
    }

    // 获取当前选择的角色对象的方法
    private Role getCurrentRole() {
        // 获取选择的角色类型
        String type = (String) roleType.getSelectedItem();
        // 根据选择的角色类型创建相应的角色对象
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

    // 将角色信息保存到文件的方法
    private void saveToFile() {
        // 获取当前选择的角色对象
        Role currentRole = getCurrentRole();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("role_data.dat"))) {
            // 将角色对象写入文件
            oos.writeObject(currentRole);
            // 弹出保存成功的提示框
            JOptionPane.showMessageDialog(this, "数据保存成功！");
        } catch (IOException ex) {
            // 弹出保存失败的提示框
            JOptionPane.showMessageDialog(this, "保存失败：" + ex.getMessage());
        }
    }

    // 从文件加载角色信息的方法
    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("role_data.dat"))) {
            // 从文件中读取角色对象
            Role role = (Role) ois.readObject();
            // 根据角色类型，设置选择框的选项
            if (role instanceof Warrior) {
                roleType.setSelectedItem("战士");
                // 更新输入框和信息显示区域
                updateFields(role, (Warrior) role);
            } else if (role instanceof Mage) {
                roleType.setSelectedItem("法师");
                // 更新输入框和信息显示区域
                updateFields(role, (Mage) role);
            } else if (role instanceof Assassin) {
                roleType.setSelectedItem("刺客");
                // 更新输入框和信息显示区域
                updateFields(role, (Assassin) role);
            }
        } catch (IOException | ClassNotFoundException ex) {
            // 弹出读取失败的提示框
            JOptionPane.showMessageDialog(this, "读取失败：" + ex.getMessage());
        }
    }

    // 更新输入框和信息显示区域的方法
    private void updateFields(Role role, Object specificRole) {
        // 将角色的生命值和攻击力显示到输入框中
        hpField.setText(String.valueOf(role.getHp()));
        attackField.setText(String.valueOf(role.getAttack()));
        // 根据角色类型，将技能和装备显示到输入框中
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
        // 显示角色信息
        showRoleInfo(role);
    }

    // 主方法，程序入口
    public static void main(String[] args) {
        // 在事件调度线程中创建并显示窗口
        SwingUtilities.invokeLater(() -> new RoleEditor().setVisible(true));
    }
}