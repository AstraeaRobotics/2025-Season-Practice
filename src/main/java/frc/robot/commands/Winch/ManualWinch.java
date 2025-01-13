// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Winch;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.WinchSubsystem;

public class ManualWinch extends Command {
  /** Creates a new ManualWinch. */

  private final WinchSubsystem m_winch;
  private final double m_speed;

  public ManualWinch(WinchSubsystem winch, double speed) {
    m_winch  = winch;
    m_speed = speed;

    addRequirements(winch);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_winch.setWinch(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_winch.setWinch(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
