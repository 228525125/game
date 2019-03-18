package org.cx.game.magic.buff;

import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.IRecover;
import org.cx.game.magic.IMagic;
import org.cx.game.observer.Observable;

public interface IBuff extends Observable,IIntercepter,IMagic,IRecover{

}
