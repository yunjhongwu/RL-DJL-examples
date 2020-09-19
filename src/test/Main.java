package test;

import ai.djl.engine.Engine;
import ai.djl.translate.TranslateException;
import main.agent.A2C;
import main.agent.DQN;
import main.agent.DynaQ;
import main.agent.QRDQN;
import main.env.Environment;
import main.env.http.HttpEnvironment;
import main.utils.Runner;

public class Main {
    public static void main(String[] args) throws TranslateException {
        Engine.getInstance().setRandomSeed(0);
        // Environment env = new CartPole(false);
        Environment env = HttpEnvironment.make("http://127.0.0.1:4000");
        env.seed(0);
        runDQN(env, 500);
    }

    public static void runDynaQ(Environment env, int goal) {
        new Runner(new DynaQ(env.getStateSpace(), env.NumOfActions(), 8, 0.1f, 0.95f, 0.05f, 8), env).run(goal);
    }

    public static void runDQN(Environment env, int goal) {
        new Runner(new DQN(env.DimOfStateSpace(), env.NumOfActions(), 64, 32, 1, 0.95f, 0.001f), env).run(goal);
    }

    public static void runA2C(Environment env, int goal) {
        new Runner(new A2C(env.DimOfStateSpace(), env.NumOfActions(), 64, 0.95f, 0.001f), env).run(goal);
    }

    public static void runQRDQN(Environment env, int goal) {
        new Runner(new QRDQN(env.DimOfStateSpace(), env.NumOfActions(), 8, 64, 32, 32, 0.95f, 0.00001f), env).run(goal);
    }
}
