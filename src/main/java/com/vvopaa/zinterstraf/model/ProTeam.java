package com.vvopaa.zinterstraf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pro_teams")
public class ProTeam extends AbstractEntity {

  @Column(name="rank")
  private String rank;

  @Column(name="img")
  private String img;

  @Column(name="name")
  private String name;

  @Column(name="earnings")
  private String earnings;

  @Column(name="points")
  private String points;

  @Column(name="players")
  private String players;

}
