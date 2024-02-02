package team.gif.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import team.gif.robot.RobotMap;

public class Indexer extends SubsystemBase {
    public static TalonSRX stageOne;
    public static CANSparkMax stageTwo;

    public static DigitalInput stageSensor;

    public boolean indexerManualFlag = false;

    public Indexer() {
        stageOne = new TalonSRX(RobotMap.STAGE_ONE);
        stageOne.configFactoryDefault();
        stageOne.setNeutralMode(NeutralMode.Brake);

        stageTwo = new CANSparkMax(RobotMap.STAGE_TWO, CANSparkLowLevel.MotorType.kBrushless);
        stageTwo.restoreFactoryDefaults();
        stageTwo.setIdleMode(CANSparkBase.IdleMode.kBrake);

        stageSensor = new DigitalInput(RobotMap.SENSOR_INDEXER_ID);
    }

    public void setIndexer(double stageOnePercent, double stageTwoPercent) {
        stageOne.set(ControlMode.PercentOutput, stageOnePercent);
        stageTwo.set(stageTwoPercent);
    }

    public boolean getSensor() {
        return stageSensor.get();
    }

    public boolean getIndexerManualFlag() {
        return indexerManualFlag;
    }
}
