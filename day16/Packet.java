import java.util.*;
import java.io.*;

public class Packet {
    private int ver, type;
    private long val;
    List<Packet> subPackets = new ArrayList<Packet>();
    public static int part1 = 0;
    
    public Packet() {
        ver = 0; type = 0; val = 0L;
    }
    
    public Packet(int v, int t, long va) {
        part1 += v;
        ver = v; type = t; val = va;
    }
    
    public Packet(int v, int t, List<Packet> subP) {
        part1 += v;
        ver = v; type = t; subPackets = subP;
        val = 0L;
    }
    
    public static int p1() {
        return part1;
    }
    
    public long p2() {
        long part2 = 0;
        Packet a, b;
        if (type >= 5 && type <= 7) { a = subPackets.get(0); b = subPackets.get(1); }
        switch (type) {
            case 0:
                for (Packet pack : subPackets) {
                    part2 += pack.p2();
                }
                break;
            case 1:
                part2 = 1;
                for (Packet pack : subPackets) {
                    part2 *= pack.p2();
                }
                break;
            case 2:
                long min = Long.MAX_VALUE;
                for (Packet pack : subPackets) {
                    min = (pack.p2() < min) ? pack.p2() : min;
                }
                part2 = min;
                break;
            case 3:
                long max = Long.MIN_VALUE;
                for (Packet pack : subPackets) {
                    max = (pack.p2() > max) ? pack.p2() : max;
                }
                part2 = max;
                break;
            case 4:
                part2 = val;
                break;
            case 5:
                Packet a = subPackets.get(0), b = subPackets.get(1);
                part2 = (a.p2() > b.p2()) ? 1L : 0L;
                break;
            case 6:
                Packet a = subPackets.get(0), b = subPackets.get(1);
                part2 = (a.p2() < b.p2()) ? 1L : 0L;
                break;
            case 7:
                Packet a = subPackets.get(0), b = subPackets.get(1);
                part2 = (a.p2() == b.p2()) ? 1L : 0L;
                break;
        }
        return part2;
    }
}
