package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;




public class AAplayer {
	private File[] list;
	public Map<String,IPlayerPlugin> Map=new HashMap<String,IPlayerPlugin>();
	public static void main(String[] arg) {
		new AAplayer();
	}
	public AAplayer() {
		AAPFrame p=new AAPFrame(this);
		GetPlugins();
		StartPlugins();
		p.Init();
	}
	
	public void GetPlugins() {
		File plugins_dir = new File("./plugins");
		if(!plugins_dir.exists() || !plugins_dir.isDirectory()) {
			list = new File[0];
			return;
		}
		
		list = plugins_dir.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.endsWith(".jar");
			}
		});
	}
	
	public void StartPlugins() {
		for(File f:list) {
			try {
				URL url = f.toURI().toURL();
				URLClassLoader classLoader = new URLClassLoader(new URL[] {url}, Thread.currentThread().getContextClassLoader());
				BufferedReader in = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream("class.txt")));
				
				String s = new String();
				while ((s=in.readLine())!=null) {
					Class pluginClass = classLoader.loadClass(s);
					IPlayerPlugin plugin = (IPlayerPlugin)pluginClass.newInstance();
					String type = plugin.type();
					if(!Map.containsKey(type)) {
						Map.put(type, plugin);
					}
				}
				in.close();
				classLoader.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public IPlayerPlugin PlayMusic(File music) {
		String musicName = music.getName();
		String suffix = musicName.substring(musicName.lastIndexOf('.'), musicName.length());
		
		if(Map.containsKey(suffix)) {
			IPlayerPlugin plugin = Map.get(suffix);
			plugin.musicin(music);
			return plugin;
		}
		return null;
	}
}
