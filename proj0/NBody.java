public class NBody {
    public static final String Path = "images/";

    public static double readRadius(String path) {
        In in = new In(path);

        int num = in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }

    public static Body[] readBodies(String path) {
        In in = new In(path);

        int num = in.readInt();
        double Radius = in.readDouble();
        Body[] BodyArray = new Body[num];
        for (int i = 0; i < num; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            BodyArray[i] = new Body(xPos, yPos, xVel, yVel, mass, img);
        }
        return BodyArray;
    }

    public static void main(String args[]) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        Body[] Bodies = NBody.readBodies(filename);
        double radius = NBody.readRadius(filename);

        StdDraw.enableDoubleBuffering();

        StdDraw.setScale(-radius, radius);

        double time = 0;
        while (time <= T) {
            int len = Bodies.length;
            double[] xForces = new double[len];
            double[] yForces = new double[len];

            for (int i = 0; i < len; i++) {
                xForces[i] = Bodies[i].calcNetForceExertedByX(Bodies);
                yForces[i] = Bodies[i].calcNetForceExertedByY(Bodies);
            }

            for (int i = 0; i < len; i++) {
                Bodies[i].update(dt, xForces[i], yForces[i]);
                Bodies[i].update(dt, xForces[i], yForces[i]);

            }

            StdDraw.picture(0, 0, Path + "starfield.jpg", 2 * radius, 2 * radius);

            for (int i = 0; i < len; i++) {
                Bodies[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }

        StdOut.printf("%d\n", Bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < Bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    Bodies[i].xxPos, Bodies[i].yyPos, Bodies[i].xxVel,
                    Bodies[i].yyVel, Bodies[i].mass, Bodies[i].imgFileName);
        }
    }
}
