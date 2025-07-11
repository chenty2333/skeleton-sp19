public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Body a) {
        double tempX = Math.pow(this.xxPos - a.xxPos, 2);
        double tempY = Math.pow(this.yyPos - a.yyPos, 2);
        double ans = Math.sqrt(tempX + tempY);
        return ans;
    }

    public double calcForceExertedBy(Body a) {
        if (this == a)
            return 0.0;
        double dis = this.calcDistance(a);
        double N = this.mass * a.mass;
        double G = 6.67e-11;
        double F = G * N * Math.pow(dis, -2);
        return F;
    }

    public double calcForceExertedByX(Body a) {
        if (this == a)
            return 0.0;
        double dx = a.xxPos - this.xxPos;
        double Fx = this.calcForceExertedBy(a) * dx * Math.pow(this.calcDistance(a), -1);
        return Fx;
    }

    public double calcForceExertedByY(Body a) {
        if (this == a)
            return 0.0;
        double dy = a.yyPos - this.yyPos;
        double Fy = this.calcForceExertedBy(a) * dy * Math.pow(this.calcDistance(a), -1);
        return Fy;
    }

    public double calcNetForceExertedBy(Body[] a) {
        double F = 0;
        for (Body s : a) {
            F += this.calcForceExertedBy(s);
        }
        return F;
    }

    public double calcNetForceExertedByX(Body[] a) {
        double Fx = 0;
        for (Body s : a) {
            Fx += this.calcForceExertedByX(s);
        }
        return Fx;
    }

    public double calcNetForceExertedByY(Body[] a) {
        double Fy = 0;
        for (Body s : a) {
            Fy += this.calcForceExertedByY(s);
        }
        return Fy;
    }

    public double calcAccelerationByX(double fX) {
        if (fX == 0)
            return 0;
        double aX = fX * Math.pow(this.mass, -1);
        return aX;
    }

    public double calcAccelerationByY(double fY) {
        if (fY == 0)
            return 0;
        double aY = fY * Math.pow(this.mass, -1);
        return aY;
    }

    public void update(double time, double fX, double fY) {
        if (time == 0)
            return;
        this.xxVel += this.calcAccelerationByX(fX) * time;
        this.yyVel += this.calcAccelerationByY(fY) * time;
        this.xxPos += this.xxVel * time;
        this.yyPos += this.yyVel * time;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
