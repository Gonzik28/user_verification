package com.gonzik28.user_verification.service;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.EnumVariant;
import com.jacob.com.Variant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public List<String> getUserNames() {
        List<String> result = new ArrayList<>();
        String query = "SELECT * FROM Win32_UserAccount";
        ActiveXComponent axWMI = new ActiveXComponent("winmgmts:\\");
        Variant vCollection = axWMI.invoke("ExecQuery", new Variant(query));
        EnumVariant enumVariant = new EnumVariant(vCollection.toDispatch());
        Dispatch item;
        StringBuilder sb = new StringBuilder();

        while (enumVariant.hasMoreElements()) {
            item = enumVariant.nextElement().toDispatch();
            result.add(sb.append(Dispatch.call(item, "Name")).toString());
            sb.setLength(0);
        }
        return result;
    }

    public String findByUserName(String userName) {
        String resultMassage = "Пользователя " + userName + " нет";
        List<String> list = getUserNames();
        for (int i = list.size() - 1; i >= 0; i--) {
            if (userName.equals(list.get(i))) {
                resultMassage = "Пользователь " + list.get(i) + " есть";
            }
        }
        return resultMassage;
    }
}
