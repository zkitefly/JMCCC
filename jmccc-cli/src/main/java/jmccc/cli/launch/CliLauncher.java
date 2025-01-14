package jmccc.cli.launch;

import jmccc.cli.download.CliDownloader;
import org.to2mbn.jmccc.launch.Launcher;
import org.to2mbn.jmccc.launch.LauncherBuilder;
import org.to2mbn.jmccc.launch.MissingDependenciesException;
import org.to2mbn.jmccc.launch.ProcessListener;
import org.to2mbn.jmccc.option.LaunchOption;
import org.to2mbn.jmccc.version.Library;


public class CliLauncher {

    public static void launch(LaunchOption option) throws Exception {
        Launcher launcher = LauncherBuilder.create().printDebugCommandline(true).build();
        //Change Minecraft main menu bottom left text
        option.commandlineVariables().put("version_type", "JMCCC 3.0");
        //Set memory to 2048MB
        option.setMaxMemory(2048);
        ProcessListener listener = new CliListener();
        System.out.println("Launching version: " + option.getVersion());
        try {
            launcher.launch(option, listener);
        } catch (MissingDependenciesException e) {
            for (Library lib : e.getMissingLibraries()) {
                CliDownloader.downloadLibrary(option.getMinecraftDirectory(), lib);
            }
            launcher.launch(option, listener);
        }
    }
}
