package question2;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.lang.*;

import java.util.Collection;

import container.*;
public class TestRelaisColis extends junit.framework.TestCase{

    public void test1() throws Exception{
        IRelaisColis rc = new RelaisColis("ABonPort");
        rc.add("a");rc.add("b");rc.add("c");rc.add("d"); // 4 destinataires : a,b,c,d

        String colis = new String("bon anniversaire"); // un colis avec un message
        rc.send(colis,"a");

        List<String> list = rc.withdrawal("a");
        System.out.println(list.toString());
        assertEquals(1,list.size());
        assertTrue(list.get(0).contains("bon anniversaire"));

        list = rc.withdrawal("a");
        assertEquals(0,list.size());

        rc.send("b");//un colis sans message
        list = rc.withdrawal("b");
        System.out.println(list.toString());
        assertEquals(1,list.size());
        assertFalse(list.get(0).isEmpty());

        list = rc.withdrawal("b");
        assertEquals(0,list.size());

        //plusieurs colis sans message
        rc.send("b");
        rc.send("b");
        rc.send("b");
        list = rc.withdrawal("b");
        System.out.println(list.toString());
        assertEquals(3,list.size());
        assertFalse(list.get(0).isEmpty());

        list = rc.withdrawal("b");
        assertEquals(0,list.size());

    }

    public void testAvecInjection() throws Exception{
        ApplicationContext ctx = container.Factory.createApplicationContext("./question2/README.TXT");
        IRelaisColis rc = ctx.getBean("relaisColis");

        // IRelaisColis rc = new RelaisColis();
        rc.add("a");rc.add("b");rc.add("c");rc.add("d"); // 4 destinataires : a,b,c,d

        String colis = new String("bon anniversaire"); // un colis avec un message
        rc.send(colis,"a");

        List<String> list = rc.withdrawal("a");
        System.out.println(list.toString());
        assertEquals(1,list.size());
        assertTrue(list.get(0).contains("bon anniversaire"));

        list = rc.withdrawal("a");
        assertEquals(0,list.size());

        rc.send("b");//un colis sans message
        list = rc.withdrawal("b");
        System.out.println(list.toString());
        assertEquals(1,list.size());
        assertFalse(list.get(0).isEmpty());

        list = rc.withdrawal("b");
        assertEquals(0,list.size());

        //plusieurs colis sans message
        rc.send("b");
        rc.send("b");
        rc.send("b");
        list = rc.withdrawal("b");
        System.out.println(list.toString());
        assertEquals(3,list.size());
        assertFalse(list.get(0).isEmpty());

        list = rc.withdrawal("b");
        assertEquals(0,list.size());

    }

    public void testAvecNotifications() throws Exception{
        IRelaisColis rc = new RelaisColis("ABonPort");
        rc.setNotif();//inscription a l option de notification
        
        IDepotListener listenerA = new DepotListener();//creation d'un listener pour "a"
        IDepotListener listenerB = new DepotListener();//creation d'un listener pour "a"
        
        rc.add("a");//inscription au relais
        rc.addDepotListener("a", listenerA);  // inscription à la notification
        rc.send("bon anniversaire","a"); 
        // System.out.println(listenerA.getNotifications().get(0));
        List<String> list = rc.withdrawal("a");
        // System.out.println(list.toString());
        assertEquals(1,list.size());
        assertTrue(list.get(0).contains("bon anniversaire"));
        assertTrue(listenerA.getNotifications().get(0).contains("bon anniversaire"));
        list = rc.withdrawal("a");
        assertEquals(0,list.size());
        
        rc.add("b");//inscription au relais
        rc.addDepotListener("b", listenerB);  // inscription à la notification
        rc.send("b");//un colis sans message
        list = rc.withdrawal("b");
        System.out.println(list.toString());
        assertEquals(1,list.size());
        assertFalse(list.get(0).isEmpty());
        assertTrue(listenerB.getNotifications().get(0).contains("Colis deposé le : "));

        list = rc.withdrawal("b");
        assertEquals(0,list.size());

        //plusieurs colis sans message
        rc.send("b");
        rc.send("b");
        rc.send("b");
        list = rc.withdrawal("b");
        System.out.println(list.toString());
        assertEquals(3,list.size());
        assertFalse(list.get(0).isEmpty());

        list = rc.withdrawal("b");
        assertEquals(0,list.size());
        
    }
}
