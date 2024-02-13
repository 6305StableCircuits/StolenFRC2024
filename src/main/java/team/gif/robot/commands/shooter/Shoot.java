package team.gif.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import team.gif.robot.Constants;
import team.gif.robot.Robot;

public class Shoot extends Command {
    boolean isFiring;
    double counter;

    public Shoot() {
        super();
        addRequirements(Robot.indexer,Robot.shooter); // uncomment
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        isFiring = false;
        counter = 0;
    }

    // Called every time the scheduler runs (~20ms) while the command is scheduled
    @Override
    public void execute() {
        if (Robot.shooter.getShooterRPM() >= (Constants.Shooter.REV_RPM * .95)) { //allow tolerance
            Robot.indexer.setIndexer(0, Constants.Indexer.STAGE_TWO);
            isFiring = true;
        } else {
            Robot.shooter.setShooterRPM(Constants.Shooter.REV_RPM);
        }

        if (isFiring) {
            counter++;
        }
    }

    // Return true when the command should end, false if it should continue. Runs every ~20ms.
    @Override
    public boolean isFinished() {
        return counter > (.5*50);
    }

    // Called when the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Robot.indexer.setIndexer(0,0);
        Robot.shooter.setVoltage(0);
    }
}
