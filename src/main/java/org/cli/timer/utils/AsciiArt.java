package org.cli.timer.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class AsciiArt {

    public static @NotNull String toString(String @NotNull [] ascii) {
        StringBuilder result = new StringBuilder();
        for (String line : ascii) { result.append(line).append('\n'); }
        return result.toString();
    }

    @Contract("_, _ -> param1")
    public static String @NotNull [] appendString(String @NotNull [] string1, String @NotNull [] string2) {
        int len = Math.min(string1.length, string2.length);
        for (int i = 0; i < len; i++) { string1[i] += string2[i]; }
        return string1;
    }

    @Contract(value = " -> new", pure = true)
    public static String @NotNull [] getTitle() {
        return new String[]{
                "╔══════════════════════════════════════════════════════════════╗",
                "║ ████████╗██╗███╗░░░███╗███████╗██████╗░  ░█████╗░██╗░░░░░██╗ ║",
                "║ ╚══██╔══╝██║████╗░████║██╔════╝██╔══██╗  ██╔══██╗██║░░░░░██║ ║",
                "║ ░░░██║░░░██║██╔████╔██║█████╗░░██████╔╝  ██║░░╚═╝██║░░░░░██║ ║",
                "║ ░░░██║░░░██║██║╚██╔╝██║██╔══╝░░██╔══██╗  ██║░░██╗██║░░░░░██║ ║",
                "║ ░░░██║░░░██║██║░╚═╝░██║███████╗██║░░██║  ╚█████╔╝███████╗██║ ║",
                "║ ░░░╚═╝░░░╚═╝╚═╝░░░░░╚═╝╚══════╝╚═╝░░╚═╝  ░╚════╝░╚══════╝╚═╝ ║",
                "╚══════════════════════════════════════════════════════════════╝"
        };
    }

    @Contract(value = " -> new", pure = true)
    public static String @NotNull [] getModeTimer() {
        return new String[]{
                "  ╔═╗╔═╗    ╔╗     ╔════╦══╦═╗╔═╦═══╦═══╗",
                "  ║║╚╝║║    ║║     ║╔╗╔╗╠╣╠╣║╚╝║║╔══╣╔═╗║",
                "  ║╔╗╔╗╠══╦═╝╠══╗╔╗╚╝║║╚╝║║║╔╗╔╗║╚══╣╚═╝║",
                "  ║║║║║║╔╗║╔╗║║═╣╚╝  ║║  ║║║║║║║║╔══╣╔╗╔╝",
                "  ║║║║║║╚╝║╚╝║║═╣╔╗  ║║ ╔╣╠╣║║║║║╚══╣║║╚╗",
                "  ╚╝╚╝╚╩══╩══╩══╝╚╝  ╚╝─╚══╩╝╚╝╚╩═══╩╝╚═╝"
        };
    }

    @Contract(value = " -> new", pure = true)
    public static String @NotNull [] getModePause() {
        return new String[]{
                "  ╔═╗╔═╗    ╔╗     ╔═══╦═══╦╗ ╔╦═══╦═══╗                        ",
                "  ║║╚╝║║    ║║     ║╔═╗║╔═╗║║ ║║╔═╗║╔══╝                        ",
                "  ║╔╗╔╗╠══╦═╝╠══╗╔╗║╚═╝║║ ║║║ ║║╚══╣╚══╗                        ",
                "  ║║║║║║╔╗║╔╗║║═╣╚╝║╔══╣╚═╝║║ ║╠══╗║╔══╝                        ",
                "  ║║║║║║╚╝║╚╝║║═╣╔╗║║  ║╔═╗║╚═╝║╚═╝║╚══╗                        ",
                "  ╚╝╚╝╚╩══╩══╩══╝╚╝╚╝  ╚╝ ╚╩═══╩═══╩═══╝                        "
        };
    }

    @Contract(value = " -> new", pure = true)
    public static String @NotNull [] getProgress() {
        return new String[]{
                "  ╔═══╗                      ",
                "  ║╔═╗║                      ",
                "  ║╚═╝╠═╦══╦══╦═╦══╦══╦══╗╔╗ ",
                "  ║╔══╣╔╣╔╗║╔╗║╔╣║═╣══╣══╣╚╝ ",
                "  ║║  ║║║╚╝║╚╝║║║║═╬══╠══║╔╗ ",
                "  ╚╝  ╚╝╚══╩═╗╠╝╚══╩══╩══╝╚╝ ",
                "           ╔═╝║              ",
                "           ╚══╝              "
        };
    }

    @Contract(value = " -> new", pure = true)
    public static String @NotNull [] getPercent() {
        return new String[]{
                "╔═╗   ╔╗",
                "╚═╝ ╔═╝║",
                "  ╔═╝╔═╝",
                "╔═╝╔═╝  ",
                "║╔═╝ ╔═╗",
                "╚╝   ╚═╝"
        };
    }

    @Contract(value = " -> new", pure = true)
    public static String @NotNull [] getOpenBrackets() {
        return new String[]{
                "╔═",
                "║ ",
                "║ ",
                "║ ",
                "║ ",
                "╚═"
        };
    }

    @Contract(value = " -> new", pure = true)
    public static String @NotNull [] getCloseBrackets() {
        return new String[]{
                "═╗",
                " ║",
                " ║",
                " ║",
                " ║",
                "═╝"
        };
    }

    public static String[] getSpaces(int _number) {
        String spaces = new String(new char[_number]).replace('\0', ' ');
        return new String[]{
                spaces,
                spaces,
                spaces,
                spaces,
                spaces,
                spaces
        };
    };

    @Contract(value = " -> new", pure = true)
    public static String @NotNull [] getOf() {
        return new String[]{
                "     ╔═╗ ",
                "     ║╔╝ ",
                " ╔══╦╝╚╗ ",
                " ║╔╗╠╗╔╝ ",
                " ║╚╝║║║  ",
                " ╚══╝╚╝  "
        };
    }

    public static String[] getNumber(int _number) {
        return switch (_number) {
            case 0 -> new String[]{
                    "╔═══╗",
                    "║╔═╗║",
                    "║║║║║",
                    "║║║║║",
                    "║╚═╝║",
                    "╚═══╝"
            };
            case 1 -> new String[]{
                    " ╔╗ ",
                    "╔╝║ ",
                    "╚╗║ ",
                    " ║║ ",
                    "╔╝╚╗",
                    "╚══╝"
            };
            case 2 -> new String[]{
                    "╔═══╗",
                    "║╔═╗║",
                    "╚╝╔╝║",
                    "╔═╝╔╝",
                    "║║╚═╗",
                    "╚═══╝"
            };
            case 3 -> new String[]{
                    "╔═══╗",
                    "║╔═╗║",
                    "╚╝╔╝║",
                    "╔╗╚╗║",
                    "║╚═╝║",
                    "╚═══╝"
            };
            case 4 -> new String[]{
                    "╔╗ ╔╗",
                    "║║ ║║",
                    "║╚═╝║",
                    "╚══╗║",
                    "   ║║",
                    "   ╚╝"
            };
            case 5 -> new String[]{
                    "╔═══╗",
                    "║╔══╝",
                    "║╚══╗",
                    "╚══╗║",
                    "╔══╝║",
                    "╚═══╝"
            };
            case 6 -> new String[]{
                    "╔═══╗",
                    "║╔══╝",
                    "║╚══╗",
                    "║╔═╗║",
                    "║╚═╝║",
                    "╚═══╝"
            };
            case 7 -> new String[]{
                    "╔═══╗",
                    "║╔═╗║",
                    "╚╝╔╝║",
                    "  ║╔╝",
                    "  ║║ ",
                    "  ╚╝ "
            };
            case 8 -> new String[]{
                    "╔═══╗",
                    "║╔═╗║",
                    "║╚═╝║",
                    "║╔═╗║",
                    "║╚═╝║",
                    "╚═══╝"
            };
            case 9 -> new String[]{
                    "╔═══╗",
                    "║╔═╗║",
                    "║╚═╝║",
                    "╚══╗║",
                    "╔══╝║",
                    "╚═══╝"
            };
            default -> new String[]{"", "", "", "", "", ""};
        };
    }
}
