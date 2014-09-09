package no.borber.monsterShop.basket;

import no.borber.monsterShop.eventsourcing.Event;
import no.borber.monsterShop.monsterTypes.MonsterTypeJson;

public class RemoveMonsterFromBasket extends Event {
  
  MonsterTypeJson monsterName;

  public RemoveMonsterFromBasket(MonsterTypeJson monsterName) {
    super();
    this.monsterName = monsterName;
  }

}
