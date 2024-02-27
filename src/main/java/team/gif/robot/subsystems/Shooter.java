package team.gif.robot.subsystems;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import team.gif.robot.Constants;
import team.gif.robot.RobotMap;

public class Shooter extends SubsystemBase {
//    public static CANSparkMax shooterNeo; //Leave for shooter Neo
    public static CANSparkFlex shooter;
    public static SparkPIDController pidShooter;

    public Shooter() {
//        shooterNeo = new CANSparkMax(RobotMap.SHOOTER_ID, CANSparkLowLevel.MotorType.kBrushless); // Leave for shooter Neo
        shooter = new CANSparkFlex(RobotMap.SHOOTER_ID, CANSparkLowLevel.MotorType.kBrushless);

        configShooter();
    }

    public void setVoltagePercent(double percent) {
//        shooterNeo.set(percent); // Leave for shooter Neo
        shooter.set(percent);
    }

    public void setShooterRPM(double rpm) {
//        pidShooter.setReference(rpm, CANSparkBase.ControlType.kVelocity); // Leave for shooter Neo
        pidShooter.setReference(rpm, CANSparkFlex.ControlType.kVelocity);
    }

    public double getShooterRPM() {
//        return shooterNeo.getEncoder().getVelocity(); // Leave for shooter Neo
        return shooter.getEncoder().getVelocity();
    }

    public void stop() {
        shooter.set(0);
    }

    public String getShooterRPM_Shuffleboard() {
        return String.format("%12.0f", getShooterRPM());
    }

    public void resetKI() {
        pidShooter.setIAccum(0.0);
        pidShooter.setIZone(1000);
    }

    public double getShooterMotorTemp() {
        return shooter.getMotorTemperature();
    }

    public boolean isShooterCool() {
        if (getShooterMotorTemp() >= Constants.MotorTemps.SHOOTER_MOTOR_TEMP) {
            return false;
        }
        return true;
    }

    /**
     *  All the config setting for Shooter (controller, pid)
     */
    public void configShooter() {
//        shooterNeo.restoreFactoryDefaults(); // Leave for shooter Neo
//        shooterNeo.setInverted(true); // Leave for shooter Neo
//        shooterNeo.setIdleMode(CANSparkBase.IdleMode.kCoast); // Leave for shooter Neo

        shooter.restoreFactoryDefaults();
        shooter.setInverted(true);
        shooter.setIdleMode(CANSparkBase.IdleMode.kCoast);

//        pidShooter = shooterNeo.getPIDController(); // Leave for shooter Neo
        pidShooter = shooter.getPIDController();

        pidShooter.setP(Constants.Shooter.kP);
        pidShooter.setFF(Constants.Shooter.FF);
        pidShooter.setI(Constants.Shooter.kI);
        pidShooter.setOutputRange(0,1);
    }
}
