package org.julie.architecture.hw1.InMemoryModel;

import org.julie.architecture.hw1.ModelElements.Camera;
import org.julie.architecture.hw1.ModelElements.Flash;
import org.julie.architecture.hw1.ModelElements.PoligonalModel;
import org.julie.architecture.hw1.ModelElements.Scene;

import java.util.List;

public class ModelStore implements IModelChanger{
    public List<PoligonalModel> Models;
    public List<Scene> Scenes;
    public List<Flash> Flashes;
    public List<Camera> Cameras;
    private List<IModelChangeObserver> changeObservers;

    @Override
    public void NotifyChange(IModelChanger sender) {
        //
    }

    public Scene getScene(int num) {
        return new Scene();
    }
}
