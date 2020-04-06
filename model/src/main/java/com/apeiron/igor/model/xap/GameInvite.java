package com.apeiron.igor.model.xap;


import com.apeiron.igor.model.db.User;
import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import lombok.*;

import javax.persistence.*;

@SpaceClass
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GameInvite {

    private String id;

    //Кто отпраивл приглашение
    private Long initiator;

    public String initiatorLogin;

    //Кто получит
    private Long invited;

    public String invitedLogin;

    private Boolean invitedUserAccept;

    private Boolean closed;

    @SpaceId(autoGenerate=true)
    @SpaceRouting
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
